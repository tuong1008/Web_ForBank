import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Login");
    }
    
    loginClick(){
        let formLogin = document.getElementById('formLogin');
        let formData = new FormData(formLogin);
        formData.append("tenServer", document.getElementById("subscribers").value);
        console.log(formData.get("userName"));
        console.log(formData.get("password"));
        console.log(formData.get("tenServer"));
    };

    load() {
        let url ="http://localhost:8080/Web_ForBank/api-subscriber";
        fetch(url)
        .then(function(response) {
            return response.json();
            })
        .then(function(subscribers){
            let x = document.getElementById("subscribers");
            for (let subscriber of subscribers) {
                let option = document.createElement("option");
                option.text = subscriber.tenCN;
                option.value = subscriber.tenServer;
                x.add(option);
                } 
            });    
    };    

    async getHtml() {
        let myPromise = new Promise(function(resolve) {
            resolve(`
            <form id="formLogin" name="formLogin">
                <select name="subscribers" id="subscribers" name="tenServer">
                </select>
                <div class="form-group">
                    <input type="text" class="form-control" id="userName" name="userName"
                        placeholder="Tên đăng nhập">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password"
                        placeholder="Mật khẩu">
                </div>
                <input type="hidden" value="login" name="action"/>
                <button class="btn btn-primary" onclick="loginClick()">Đăng nhập</button>
            </form>`);
          });        
    }
}