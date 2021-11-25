import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Employee");
    }

    setEventBtn(){
        document.getElementById("addBtn").addEventListener("click", function(event) {
            event.preventDefault();
            document.querySelector("#app").innerHTML =`
            <h2 id="errorMsg"></h2>
            <form id="formSignUp" name="formSignUp">
                <div class="form-group">
                    <input type="text" class="form-control" id="ho" name="ho"
                        placeholder="Họ">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="ten" name="ten"
                        placeholder="Tên">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="diaChi" name="diaChi"
                        placeholder="Địa chỉ">
                </div>

                <select id="phai">
                    <option value="Nam" selected>Nam</option>
                    <option value="Nữ">Nữ</option>
                </select>

                <div class="form-group">
                    <input type="text" class="form-control" id="soDT" name="soDT"
                        placeholder="Số điện thoại">
                </div>
                
                <div class="form-group">
                    <input type="text" class="form-control" id="lgName" name="lgName"
                        placeholder="User Login">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="pass" name="pass"
                        placeholder="Password">
                </div>
                <button id="signUpBtn" class="btn btn-primary">Đăng ký</button>
            </form>`;
            document.getElementById("signUpBtn").addEventListener("click", function(event){
                let formSignUp = document.getElementById('formSignUp');
                let formData = new FormData(formSignUp);
                formData.append("phai", document.getElementById("phai").value);
                var object = {};
                formData.forEach(function(value, key){
                    object[key] = value;
                });
                console.log(object);
                let url = "http://localhost:8080/Web_ForBank/api-employee";
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
                    })
                    .catch(err => {
                        //login fail, show message error that return by json
                        document.getElementById("errorMsg").innerHTML =  "Sai tài khoản hoặc mật khẩu!";
                    });
                event.preventDefault();
            });
        });
    }

    load() {
        let url = "http://localhost:8080/Web_ForBank/api-employee";
        fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (employees) {
                console.log(employees);
                let x = document.getElementById("tblEmployee");
                for (let employee of employees) {
                    let row = document.createElement("TR");
                    row.innerHTML = `
                    <td>${employee.maNV}</td>
                    <td>${employee.ho}</td>
                    <td>${employee.ten}</td>
                    <td>${employee.diaChi}</td>
                    <td>${employee.phai}</td>
                    <td>${employee.soDT}</td>
                    <td>${employee.trangThaiXoa}</td>`
                    x.appendChild(row);
                }
            });
    };

    getHtml() {
        return `
        <button id="addBtn" class="btn btn-primary">Thêm Nhân Viên</button>
        <table id="tblEmployee">
        <tr>
          <th>Mã Nhân Viên</th>
          <th>Họ</th>
          <th>Tên</th>
          <th>Địa chỉ</th>
          <th>Phái</th>
          <th>Số Điện Thoại</th>
          <th>Trạng Thái</th>
        </tr>
      </table>
        `;
    }
}