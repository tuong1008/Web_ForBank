
import Account from "./view/Account.js";
import Customer from "./view/Customer.js";
import CustomerInfo from "./view/CustomerInfo.js";
import Deposit_Withdraw from "./view/Deposit_Withdraw.js";
import Employee from "./view/Employee.js";
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
        { path: "/customer/:id", view: CustomerInfo},
        { path: "/account", view: Account},
        { path: "/login", view: Login},
        { path: "/employee", view: Employee}
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

    document.querySelector("#app").innerHTML = await view.getHtml();

    if (view instanceof Login) view.load();
};

const navigateTo = url => {
    history.pushState(null, null, url);
    router();
};

window.addEventListener("popstate", router);

document.addEventListener("DOMContentLoaded", () => {
    document.body.addEventListener("click", e => {
        if (e.target.matches("[data-link]")) {
            e.preventDefault();
            navigateTo(e.target.href);
        }
    });

    router()
});

// window.onload = router;