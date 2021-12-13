import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Chuyển tiền");
    }

    setEventBtn(callback) {
        document.getElementById("addBtn").addEventListener("click", function (event) {
            event.preventDefault();
            document.querySelector("#app").innerHTML = `
            <h2 id="errorMsg"></h2>
            <form id="formSignUp" name="formSignUp">
                <div class="form-group">
                    <input type="text" class="form-control" id="soTK_Chuyen" name="soTK_Chuyen"
                        placeholder="Tài Khoản Chuyển">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="soTien" name="soTien"
                        placeholder="Số Tiền">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="soTK_Nhan" name="soTK_Nhan"
                        placeholder="Tài Khoản Nhận">
                </div>
                <button id="signUpBtn" class="btn btn-primary">Xác Nhận</button>
            </form>`;
            //form validation
            $("#formSignUp").validate({
                onkeyup: function (element) {
                    $(element).valid();
                },
                rules: {
                    soTK_Chuyen: {
                        required: true,
                        digits: true,
                        maxlength: 9
                    },
                    soTK_Nhan: {
                        required: true,
                        digits: true,
                        maxlength: 9
                    },
                    soTien: {
                        required: true,
                        digits: true,
                        min: 100000
                    }
                },
                messages: {
                    soTK_Chuyen: {
                        required: "Bắt buộc nhập Số tài khoản",
                        digits: "Bắt buộc nhập số",
                        maxlength: "Hãy nhập tối đa 9 ký tự"
                    },
                    soTK_Nhan: {
                        required: "Bắt buộc nhập Số tài khoản",
                        digits: "Bắt buộc nhập số",
                        maxlength: "Hãy nhập tối đa 9 ký tự"
                    },
                    soTien: {
                        required: "Bắt buộc nhập Số Tiền",
                        digits: "Bắt buộc nhập số",
                        min: "Giao dịch tối thiểu 100000"
                    }
                }
            });
            //end form validation
            document.getElementById("signUpBtn").addEventListener("click", function (event) {
                if (!$("#formSignUp").valid()) return;
                let formSignUp = document.getElementById('formSignUp');
                let formData = new FormData(formSignUp);
                var object = {};
                formData.forEach(function (value, key) {
                    object[key] = value;
                });
                console.log(object);
                let url = "http://localhost:8080/web_forbank/api-money-tranfer";
                fetch(url, {
                    method: "POST",
                    credentials: 'include',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(object)
                })
                    .then(function (response) {
                        return response.json();
                    })
                    .then(result => {
                        console.log(result);
                        if ((result.message).includes("thành công")) {
                            callback();
                        } else {
                            document.getElementById("errorMsg").innerHTML = result.message;
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    });
                event.preventDefault();
            });
        });
    }

    load() {
        let url = "http://localhost:8080/web_forbank/api-money-tranfer";
        fetch(url, {credentials: 'include'})
            .then(function (response) {
                return response.json();
            })
            .then(function (trans) {
                console.log(trans);
                let x = document.getElementById("tblTran");
                for (let tran of trans) {
                    let row = document.createElement("TR");
                    row.innerHTML = `
                    <td>${tran.maGD}</td>
                    <td>${tran.soTK_Chuyen}</td>
                    <td>${tran.ngayGD}</td>
                    <td>${tran.soTien}</td>
                    <td>${tran.soTK_Nhan}</td>
                    <td>${tran.maNV}</td>
                    `
                    x.appendChild(row);
                }
            });
    };

    getHtml() {
        return `
<button id="addBtn" class="btn btn-primary">Chuyển Tiền</button>
<table id="tblTran" class="table-primary">
    <thead>
    <tr>
        <th>Mã Giao Dịch</th>
        <th>Tài Khoản Chuyển</th>
        <th>Ngày Giao Dịch</th>
        <th>Số Tiền</th>
        <th>Tài Khoản Nhận</th>
        <th>Mã Nhân Viên</th>
    </tr>
    </thead>
</table>
        `;
    }
}