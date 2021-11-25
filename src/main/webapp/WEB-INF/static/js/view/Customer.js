import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Customer");
    }

    async getHtml() {
        return `
            <h1>Welcome back, Dom</h1>
            <p>
                Customer
            </p>
            <p>
                <a href="/posts" data-link>View recent posts</a>.
            </p>
        `;
    }
}