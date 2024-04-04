const express = require("express");
const router = express.Router();

const { getPaises, savePaises } = require("../controller/paises.controller.js");

router.route("/").get(getPaises);
router.route("/populate").get(savePaises);
router.route("/search/:filter").get(savePaises);

module.exports = router;