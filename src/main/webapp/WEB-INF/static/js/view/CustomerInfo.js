import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("CustomerInfo");
    }

    async getHtml() {
        console.log(this.params.id);
        return `
            <h1>Welcome back, Dom</h1>
            <p>
                Customer Info
            </p>
            <p>
                <a href="/posts" data-link>View recent posts</a>.
            </p>
        `;
    }
}