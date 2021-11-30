import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Customer");
    }

    setDeleteEvent(callback){
        if (confirm("Are you sure DELETE!")) {
            let cmnd = this.params.id;
            let object = {'cmnd': cmnd};
            let url = "http://localhost:8080/Web_ForBank/api-customer";
            fetch(url, {
                method: "DELETE",
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
        }
    }

    setEventBtn(callback){
        document.getElementById("addBtn").addEventListener("click", function(event) {
            event.preventDefault();
            document.querySelector("#app").innerHTML =`
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
                    <option value="Nam" selected>Nam</option>
                    <option value="Nữ">Nữ</option>
                </select>

                <div class="form-group">
                    <input type="text" class="form-control" id="ngayCap" name="ngayCap"
                        placeholder="Ngày Cấp">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="soDT" name="soDT"
                        placeholder="Số điện thoại">
                </div>
                
                <div class="form-group">
                    <input type="text" class="form-control" id="soDu" name="soDu"
                        placeholder="Số Dư">
                </div>
                <button id="signUpBtn" class="btn btn-primary">Mở Tài Khoản</button>
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
                let url = "http://localhost:8080/Web_ForBank/api-customer";
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
        });
    }

    load() {
        let url = "http://localhost:8080/Web_ForBank/api-customer";
        fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (customers) {
                console.log(customers);
                let x = document.getElementById("tblCustomer");
                for (let customer of customers) {
                    let row = document.createElement("TR");
                    row.innerHTML = `
                    <td>${customer.cmnd}</td>
                    <td>${customer.ho}</td>
                    <td>${customer.ten}</td>
                    <td>${customer.diaChi}</td>
                    <td>${customer.phai}</td>
                    <td>${customer.ngayCap}</td>
                    <td>${customer.soDT}</td>
                    <td>
                    <a href="/customerUpdate/${customer.cmnd}" data-link>U</a>
                    <a href="/customerDelete/${customer.cmnd}" data-link>D</a></td>`;
                    x.appendChild(row);
                }
            });
    };

    getHtml() {
        return `
        <button id="addBtn" class="btn btn-primary">Thêm Khách Hàng</button>
        <table id="tblCustomer">
        <tr>
            <th>CMND</th>
            <th>Họ</th>
            <th>Tên</th>
            <th>Địa chỉ</th>
            <th>Phái</th>
            <th>Ngày Cấp</th>
            <th>Số Điện Thoại</th>
       </tr>
      </table>
        `;
    }
}