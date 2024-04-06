const express = require("express");
const app = express();
const router = require("./src/router/index.route.js");

app.use(express.json());
app.use("/", router);

app.listen("8080", () => {
    console.log("Server running on port 8080");
});