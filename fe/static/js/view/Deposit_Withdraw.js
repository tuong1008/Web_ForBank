import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("D_W");
    }

    setEventBtn(callback){
        document.getElementById("addBtn").addEventListener("click", function(event) {
            event.preventDefault();
            document.querySelector("#app").innerHTML =`
            <h2 id="errorMsg"></h2>
            <form id="formSignUp" name="formSignUp">
                <div class="form-group">
                    <input type="text" class="form-control" id="soTK" name="soTK"
                        placeholder="Số Tài Khoản">
                </div>

                <select id="loaiGD">
                    <option value="GT" selected>Gởi Tiền</option>
                    <option value="RT">Rút Tiền</option>
                </select>

                <div class="form-group">
                    <input type="text" class="form-control" id="soTien" name="soTien"
                        placeholder="Số Tiền">
                </div>
                <button id="signUpBtn" class="btn btn-primary">Xác nhận</button>
            </form>`;
            document.getElementById("signUpBtn").addEventListener("click", function(event){
                let formSignUp = document.getElementById('formSignUp');
                let formData = new FormData(formSignUp);
                formData.append("loaiGD", document.getElementById("loaiGD").value);
                var object = {};
                formData.forEach(function(value, key){
                    object[key] = value;
                });
                console.log(object);
                let url = "http://localhost:8080/Web_ForBank/api-deposit-withdraw";
                fetch(url, {
                    method: "POST",
                    credentials: 'include',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(object)
                })
                    .then(function (response) {
                        return response.json();
                    })
                    .then(success => {
                        console.log(success);
                        callback();
                    })
                    .catch(err => {
                        //login fail, show message error that return by json
                        document.getElementById("errorMsg").innerHTML =  err;
                    });
                event.preventDefault();
            });
        });
    }

    load() {
        let url = "http://localhost:8080/Web_ForBank/api-deposit-withdraw";
        fetch(url)
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
                    <td>${tran.soTK}</td>
                    <td>${tran.loaiGD}</td>
                    <td>${tran.ngayGD}</td>
                    <td>${tran.soTien}</td>
                    <td>${tran.maNV}</td>`
                    x.appendChild(row);
                }
            });
    };

    getHtml() {
        return `
        <button id="addBtn" class="btn btn-primary">Gởi hoặc Rút</button>
        <table id="tblTran">
        <tr>
          <th>Mã Giao Dịch</th>
          <th>Số Tài Khoản</th>
          <th>Loại</th>
          <th>Ngày Giao Dịch</th>
          <th>Số Tiền</th>
          <th>Mã Nhân Viên</th>
        </tr>
      </table>
        `;
    }
}