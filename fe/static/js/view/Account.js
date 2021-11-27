import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Account");
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
                    let row = document.createElement("TR");
                    row.innerHTML = `
                    <td>${account.soTK}</td>
                    <td>${account.cmnd}</td>
                    <td>${account.soDu}</td>
                    <td>${account.maCN}</td>
                    <td>${account.ngayMoTK}</td>
                    `
                    x.appendChild(row);
                }
            });
    };

    getHtml() {
        return `
        
        <table id="tblAccount">
        <tr>
          <th>Số Tài Khoản</th>
          <th>CMND</th>
          <th>Số Dư</th>
          <th>Mã Chi Nhánh</th>
          <th>Ngày Mở TK</th>
        </tr>
      </table>
        `;
    }
}