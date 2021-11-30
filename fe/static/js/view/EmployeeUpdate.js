import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("EmployeeUpdate");
    }

    setEventBtn(callback){
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
                method: "PUT",
                credentials: 'include',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(object)
            })
                .then(function (response) {
                    return response.json();
                })
                .then(result => {
                    console.log(result);
                    if ((result.message).includes("thành công")){
                        callback();
                    }
                    else{
                        document.getElementById("errorMsg").innerHTML =  result.message;
                    }
                })
                .catch(err => {
                    console.log(err);
                });
            event.preventDefault();
        });
    }

    load() {
        let url = `http://localhost:8080/Web_ForBank/api-employee?maNV=${this.params.id}`;
        fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (employee) {
                console.log(employee);
                document.getElementById("maNV").value = employee.maNV;
                document.getElementById("ho").value = employee.ho;
                document.getElementById("ten").value = employee.ten;
                document.getElementById("diaChi").value = employee.diaChi;
                document.getElementById("phai").value = employee.phai;
                document.getElementById("soDT").value = employee.soDT;
                document.getElementById("pass").value = employee.pass;
            });
    };


    getHtml() {
        console.log(this.params.id);
        return `
        <h2 id="errorMsg"></h2>
        <form id="formSignUp" name="formSignUp">
            <div class="form-group">
                <input type="text" class="form-control" id="maNV" name="maNV"
                    placeholder="Mã Nhân Viên">
            </div>
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
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
            </select>

            <div class="form-group">
                <input type="text" class="form-control" id="soDT" name="soDT"
                    placeholder="Số Điện Thoại">
            </div>

            <div class="form-group">
                <input type="password" class="form-control" id="pass" name="pass"
                    placeholder="Password">
            </div>

            <button id="signUpBtn" class="btn btn-primary">Cập Nhật</button>
        </form>
        `;
    }
}