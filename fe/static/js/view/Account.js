import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Tài khoản");
    }

    load() {
        let url = "http://localhost:8080/Web_ForBank/api-account";
        fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (accounts) {
                console.log(accounts);
                let x = document.getElementById("tblAccount");
                for (let account of accounts) {
                    let birthday = new Date(account.ngayMoTK);
                    let row = document.createElement("TR");
                    row.innerHTML = `
                    <td>${account.soTK}</td>
                    <td>${account.cmnd}</td>
                    <td>${account.soDu}</td>
                    <td>${account.maCN}</td>
                    <td>${birthday.getDate()}-${birthday.getMonth() + 1}-${birthday.getFullYear()} ${birthday.getHours()}:${birthday.getMinutes()}</td>
                    <td><a class="text-success" href="stat/${account.soTK}">Thống kê</a></td>
                    `
                    x.appendChild(row);
                }
            });
    };

    getHtml() {
        return `
<h1 class="text-danger fs-1">Ok</h1>
<table class="table-dark" id="tblAccount">
    <thead>
    <tr>
        <th>Số Tài Khoản</th>
        <th>CMND</th>
        <th>Số Dư</th>
        <th>Mã Chi Nhánh</th>
        <th>Ngày Mở TK</th>
        <th>Khác</th>
    </tr>
    </thead>
</table>
`;
    }
}