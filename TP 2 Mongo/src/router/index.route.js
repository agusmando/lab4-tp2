const express = require("express");
const router = express.Router();

const {
    getPaises,
    getOnePaises,
    savePaises,
    searchPaises,
    deletePaises,
    updatePais,
} = require("../controller/paises.controller.js");

router.route("/paises").get(getPaises);
router.route("/paises/populate").get(savePaises);
router.route("/paises/search").get(searchPaises);
router.route("/paises/update").put(updatePais);
router.route("/paises/:codigo").delete(deletePaises).get(getOnePaises);

module.exports = router;