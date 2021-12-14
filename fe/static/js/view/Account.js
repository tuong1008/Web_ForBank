import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Tài khoản");
    }

    setDeleteEvent(callback) {
        if (confirm("Are you sure DELETE!")) {
            let soTK = this.params.id;
            let object = {'soTK': soTK};
            let url = "http://localhost:8080/web_forbank/api-account";
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
                    if ((result.message).includes("thành công")) {
                        callback();
                        document.getElementById("undoBtn").disabled = false;
                    } else {
                        document.getElementById("errorMsg").innerHTML = result.message;
                    }
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }

    setUndoEvent(callback) {
        document.getElementById("undoBtn").addEventListener("click", function (event) {
            event.preventDefault();
            let url = "http://localhost:8080/web_forbank/api-undo";
            fetch(url, {credentials: 'include'})
                .then(function (response) {
                    return response.json();
                })
                .then(result => {
                    console.log(result);
                    if ((result.message).includes("Hết")) {
                        callback();
                        document.getElementById("undoBtn").disabled = true;
                    } else if ((result.message).includes("thành công")) {
                        callback();
                        document.getElementById("undoBtn").disabled = false;
                    } else {
                        document.getElementById("errorMsg").innerHTML = result.message;
                    }
                })
                .catch(err => {
                    console.log(err);
                });
        });
    }

    load(callback) {
        let url = "http://localhost:8080/web_forbank/api-account";
        fetch(url, {credentials: 'include'})
            .then(function (response) {
                return response.json();
            })
            .then(function (accounts) {
                console.log(accounts);
                let x = document.getElementById("table");
                let body = document.createElement("tbody");
                x.appendChild(body);
                for (let account of accounts) {
                    let birthday = new Date(account.ngayMoTK);
                    let row = document.createElement("TR");
                    row.innerHTML = `
                    <td>${account.soTK}</td>
                    <td>${account.cmnd}</td>
                    <td>${account.soDu}</td>
                    <td>${account.maCN}</td>
                    <td>${birthday.getDate()}-${birthday.getMonth() + 1}-${birthday.getFullYear()} ${birthday.getHours()}:${birthday.getMinutes()}</td>
                    <td><div>
                    <a class="link-primary" href="/accountDelete/${account.soTK}" data-link>D</a>
                    <a class="text-success" href="stat/${account.soTK}">Thống kê</a>
                    </div></td>
                    `
                    body.appendChild(row);
                }
            })
            .catch(err => {
                console.log(err);
                callback();
            });
    };

    getHtml() {
        return `
<button id="undoBtn" class="btn btn-primary" disabled>Hoàn Tác</button>
<h2 id="errorMsg"></h2>
<table id="table" class="table table-primary">
<thead>
    <tr>
        <th>Số Tài Khoản</th>
        <th>CMND</th>
        <th>Số Dư</th>
        <th>Mã Chi Nhánh</th>
        <th>Ngày Mở TK</th>
        <th></th>
    </tr>
</thead>
</table>
`;
    }
}