
import Account from "./view/Account.js";
import Customer from "./view/Customer.js";
import CustomerUpdate from "./view/CustomerUpdate.js";
import Deposit_Withdraw from "./view/Deposit_Withdraw.js";
import Employee from "./view/Employee.js";
import EmployeeUpdate from "./view/EmployeeUpdate.js";
import Money_Tranfer from "./view/Money_Tranfer.js";
import Login from "./view/Login.js";

const pathToRegex = path => new RegExp("^" + path.replace(/\//g, "\\/").replace(/:\w+/g, "(.+)") + "$");

const getParams = match => {
    const values = match.result.slice(1);
    const keys = Array.from(match.route.path.matchAll(/:(\w+)/g)).map(result => result[1]);

    return Object.fromEntries(keys.map((key, i) => {
        return [key, values[i]];
    }));
};



const router = async () => {
    const routes = [
        { path: "/money-transfer", view: Money_Tranfer},
        { path: "/deposit-withdraw", view: Deposit_Withdraw},
        { path: "/customer", view: Customer},
        { path: "/customerUpdate/:id", view: CustomerUpdate},
        { path: "/customerDelete/:id", view: Customer},
        { path: "/account", view: Account},
        { path: "/login", view: Login},
        { path: "/logout", view: Login},
        { path: "/employee", view: Employee},
        { path: "/employeeDelete/:id", view: Employee},
        { path: "/employeeUpdate/:id", view: EmployeeUpdate}
    ];

    // Test each route for potential match
    const potentialMatches = routes.map(route => {
        return {
            route: route,
            result: location.pathname.match(pathToRegex(route.path))
        };
    });

    let match = potentialMatches.find(potentialMatch => potentialMatch.result !== null);

    //default match
    if (!match) {
        match = {
            route: routes[0],
            result: [location.pathname]
        };
    }

    const view = new match.route.view(getParams(match));
    if (view instanceof Login){
        document.querySelector("#app").innerHTML =  view.getHtml();
        view.load();
        view.setEventBtn(function(){
            navigateTo("/deposit-withdraw");
        });
        if (match.route.path == "/logout"){
            view.editViewAfterLogout();
        }
    }
    else if (view instanceof Employee){
        if (match.route.path.includes("employeeDelete")){
            view.setDeleteEvent(function(){
                navigateTo("/employee");
            });
        }
        else{
            document.querySelector("#app").innerHTML =  view.getHtml();
            view.load();
            view.setEventBtn(function(){
                navigateTo("/employee");
            });
        }
    }
    else if (view instanceof EmployeeUpdate){
        document.querySelector("#app").innerHTML =  view.getHtml();
        view.load();
        view.setEventBtn(function(){
            navigateTo("/employee");
        });
    }
    else if (view instanceof Customer){
        if (match.route.path.includes("customerDelete")){
            view.setDeleteEvent(function(){
                navigateTo("/customer");
            });
        }
        else{
            document.querySelector("#app").innerHTML =  view.getHtml();
            view.load();
            view.setEventBtn(function(){
                navigateTo("/customer");
            });
        }
    }
    else if (view instanceof CustomerUpdate){
        document.querySelector("#app").innerHTML =  view.getHtml();
        view.load();
        view.setEventBtn(function(){
            navigateTo("/customer");
        });
    }
    else if (view instanceof Money_Tranfer){
        document.querySelector("#app").innerHTML =  view.getHtml();
        view.load();
        view.setEventBtn(function(){
            navigateTo("/money-transfer");
        });
    }
    else if (view instanceof Deposit_Withdraw){
        document.querySelector("#app").innerHTML =  view.getHtml();
        view.load();
        view.setEventBtn(function(){
            navigateTo("/deposit-withdraw");
        });
    }
    else{
        document.querySelector("#app").innerHTML =  view.getHtml();
        view.load();
    }
};

const navigateTo = url => {
    history.pushState(null, null, url);
    router();
};

window.addEventListener("popstate", router);

$.validator.addMethod("validateSoDT", function (value, element) {
    return this.optional(element) || /^0[0-9]{9,10}$/i.test(value);
}, "Hãy nhập đúng định dạng");

$.validator.addMethod("validateTiengViet", function (value, element) {
    return /^[^0-9`~!@#$%^&*()_=\\+<,.>\/?;:'"[{\]}|]+$/i.test(value);
}, "Hãy nhập chữ cái tiếng Việt");

$.validator.addMethod("validateNgayCap", function (value, element) {
    let ngayCap = new Date(value);
    let hienTai = new Date();
    return Math.abs(hienTai-ngayCap)>= 504911232000;
}, "Phải đủ 16 tuổi trở lên");

document.addEventListener("DOMContentLoaded", ()=>{
    document.body.addEventListener("click", e => {
        if (e.target.matches("[data-link]")) {
            e.preventDefault();
            navigateTo(e.target.href);
        }
    });
});

// window.onload = router;