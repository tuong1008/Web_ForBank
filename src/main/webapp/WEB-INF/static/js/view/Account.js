import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Account");
    }

    async getHtml() {
        return `
            <h1>Welcome back, Dom</h1>
            <p>
                Account
            </p>
            <p>
                <a href="/posts" data-link>View recent posts</a>.
            </p>
        `;
    }
}