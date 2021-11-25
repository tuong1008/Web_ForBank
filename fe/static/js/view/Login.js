import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Login");
    }

    load() {
        let url = "http://localhost:8080/Web_ForBank/api-subscriber";
        fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (subscribers) {
                let x = document.getElementById("subscribers");
                for (let subscriber of subscribers) {
                    let option = document.createElement("option");
                    option.text = subscriber.tenCN;
                    option.value = subscriber.tenServer;
                    x.add(option);
                }
            });
    };

    setEventBtn(callback){
        document.getElementById("loginBtn").addEventListener("click", function(event) {
            let formLogin = document.getElementById('formLogin');
            let formData = new FormData(formLogin);
            formData.append("tenServer", document.getElementById("subscribers").value);
            console.log(formData.get("userName"));
            console.log(formData.get("password"));
            console.log(formData.get("tenServer"));
            let url = "http://localhost:8080/Web_ForBank/api-user-login";
            fetch(url, {
                credentials: 'include',
                headers: {
                    "user": formData.get("userName"),
                    "password": formData.get("password"),
                    "serverName": formData.get("tenServer")
                }
            })
                .then(function (response) {
                    return response.json();
                })
                .then(success => {
                    console.log(success);
                    document.getElementById("nav_item_login").hidden=true;
                    document.getElementById("nav_item_logout").hidden=false;
                    return callback();
                })
                .catch(err => {
                    //login fail, show message error that return by json
                    document.getElementById("errorMsg").innerHTML =  "Sai tài khoản hoặc mật khẩu!";
                });
                event.preventDefault();
        });
    }

    editViewAfterLogout(){
        document.getElementById("nav_item_login").hidden=false;
        document.getElementById("nav_item_logout").hidden=true;
    }

    getHtml() {
        return `
        <h2 id="errorMsg"></h2>
    <form id="formLogin" name="formLogin">
        <select name="subscribers" id="subscribers"">
        </select>
        <div class="form-group">
            <input type="text" class="form-control" id="userName" name="userName"
                placeholder="Tên đăng nhập">
        </div>

        <div class="form-group">
            <input type="password" class="form-control" id="password" name="password"
                placeholder="Mật khẩu">
        </div>
        <button id="loginBtn" class="btn btn-primary">Đăng nhập</button>
    </form>`;
    }
}