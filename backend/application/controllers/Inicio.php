<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class Inicio extends MY_Controller {
    public function index() { $this->printWs(array("message" => "Bem vindo ao WS!")); }
}
