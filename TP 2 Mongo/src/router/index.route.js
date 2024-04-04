const express = require("express");
const router = express.Router();

const {
    getPaises,
    savePaises,
    searchPaises,
} = require("../controller/paises.controller.js");

router.route("/").get(getPaises);
router.route("/populate").get(savePaises);
router.route("/search/:filter").get(searchPaises);

module.exports = router;