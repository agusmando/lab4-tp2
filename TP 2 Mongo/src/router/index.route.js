const express = require("express");
const router = express.Router();

const {
    getPaises,
    getOnePaises,
    savePaises,
    searchPaises,
    deletePaises
} = require("../controller/paises.controller.js");

router.route("/paises").get(getPaises);
router.route("/paises/populate").get(savePaises);
router.route("/paises/search").get(searchPaises);
router.route("/paises/:codigo").delete(deletePaises).get(getOnePaises);

module.exports = router;