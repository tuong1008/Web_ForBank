const express = require("express");
const app = express();
const path = require("path");

app.use("/static", express.static(path.resolve(__dirname, "fe", "static")));
// app.use(express.static(path.join(__dirname, '/public')));
//
// app.get("/css/**", (req, res) => {
//     // res.sendFile(path.resolve(__dirname,"/public"));
//     let uri = 'http://localhost:5000' + req.path;
//     res.redirect(uri);
// });

app.all("/*", (req, res) => {
    res.sendFile(path.resolve(__dirname,"fe", "index.html"));
});

app.listen(process.env.PORT || 5000, () => console.log("Server running..."));
