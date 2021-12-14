import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Sao kê");
    }

    setEventBtn(){
        //form validation
        $("#formSignUp").validate({
            onkeyup: function(element) {
                $(element).valid();
            },
            rules: {
                tuNgay: {
                    required: true,
                    date: true
                },
                denNgay: {
                    required: true,
                    date: true
                }
            },
            messages: {
                tuNgay: {
                    required: "Bắt buộc nhập ngày",
                    date: "Nhập sai định dạng"
                },
                denNgay: {
                    required: "Bắt buộc nhập ngày",
                    date: "Nhập sai định dạng"
                }
            }
        });
        //end form validation
        let soTK = this.params.id;
        document.getElementById("signUpBtn").addEventListener("click", function(event){
            event.preventDefault();
            if (!$("#formSignUp").valid()) return;
            let formSignUp = document.getElementById('formSignUp');
            let formData = new FormData(formSignUp);
            let url = `http://localhost:8080/web_forbank/api-bank-account-statement?soTK=${soTK}&tuNgay=${formData.get("tuNgay")}&denNgay=${formData.get("denNgay")}`;
            fetch(url, {credentials: 'include'})
            .then(function (response) {
                return response.json();
            })
            .then(function (trans) {
                console.log(trans);
                let x = document.getElementById("table");
                let body = document.createElement("tbody");
                x.appendChild(body);
                for (let tran of trans) {
                    let ngayGD = new Date(tran.ngayGD);
                    let row = document.createElement("TR");
                    
                    row.innerHTML = `
                        <td>${tran.balanceBefore}</td>
                        <td>${ngayGD.getDate()}-${ngayGD.getMonth() + 1}-${ngayGD.getFullYear()} ${ngayGD.getHours()}:${ngayGD.getMinutes()}</td>
                        <td>${tran.loaiGD}</td>
                        <td>${tran.soTien}</td>
                        <td>${tran.balanceAfter}</td>
                        `
                    body.appendChild(row);
                }
            })
            .catch(err => {
                console.log(err);
            });
        });
    }

    // load() {
    //     let tuNgay = document.getElementById("tuNgay").value;
    //     let denNgay = document.getElementById("denNgay").value;
    // };

    getHtml() {
        return `
        <form id="formSignUp" name="formSignUp">
            <div class="form-group">
                <input type="date" class="form-control" id="tuNgay" name="tuNgay" placeholder="Từ ngày">
                </div>
            <div class="form-group">
                <input type="date" class="form-control" id="denNgay" name="denNgay" placeholder="Đến ngày">
            </div>
            <button id="signUpBtn" class="btn btn-primary">Liệt kê</button>
        </form>
        <table id="table" class="table table-primary">
        <thead>
            <tr>
                <th>Số dư đầu</th>
                <th>Ngày</th>
                <th>Loại giao dịch</th>
                <th>Số tiền</th>
                <th>Số dư sau</th>
            </tr>
        </thead>
        </table>
        `;
    }
}