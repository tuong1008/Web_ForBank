import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("CustomerUpdate");
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
            let url = "http://localhost:8080/Web_ForBank/api-customer";
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
        let url = `http://localhost:8080/Web_ForBank/api-customer?cmnd=${this.params.id}`;
        fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (customer) {
                console.log(customer);
                document.getElementById("cmnd").value = customer.cmnd;
                document.getElementById("ho").value = customer.ho;
                document.getElementById("ten").value = customer.ten;
                document.getElementById("diaChi").value = customer.diaChi;
                document.getElementById("phai").value = customer.phai;
                document.getElementById("ngayCap").value = customer.ngayCap;
                document.getElementById("soDT").value = customer.soDT;
            });
    };


    getHtml() {
        console.log(this.params.id);
        return `
        <h2 id="errorMsg"></h2>
        <form id="formSignUp" name="formSignUp">
            <div class="form-group">
                <input type="text" class="form-control" id="cmnd" name="cmnd"
                    placeholder="CMND">
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
                <input type="text" class="form-control" id="ngayCap" name="ngayCap"
                    placeholder="Ngày Cấp">
            </div>

            <div class="form-group">
                <input type="text" class="form-control" id="soDT" name="soDT"
                    placeholder="Số Điện Thoại">
            </div>

            <button id="signUpBtn" class="btn btn-primary">Cập Nhật</button>
        </form>
        `;
    }
}