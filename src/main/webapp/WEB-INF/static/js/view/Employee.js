import AbstractView from "./AbstractView.js";

export default class extends AbstractView {
    constructor(params) {
        super(params);
        this.setTitle("Employee");
    }

    async getHtml() {
        return `
            <h1>Welcome back, Dom</h1>
            <p>
                Employee
            </p>
            <p>
                <a href="/posts" data-link>View recent posts</a>.
            </p>
        `;
    }
}