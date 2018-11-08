<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends MY_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('LoginModel');
    }

    public function index() {
        switch ($this->getRequestType()) {
            case "post":
                $this->reqLogin();
                break;
        }
    }

    private function reqLogin() {
        $usuario = addslashes($this->input->post("usuario"));
        $senha = addslashes($this->input->post("senha"));

        if(empty($usuario) || empty($senha))
            $this->setError("Usuário e senha devem ser preenchidos");

        $ret = false;
        if($this->valid) $ret = $this->LoginModel->login($usuario, $senha);
        $this->printWs($ret);
    }
}