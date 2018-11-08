<?php

class LoginModel extends CI_Model {

    public function __construct() {
        parent::__construct();
    }

    public function login($usuario, $senha) {

        $query = "SELECT usuario FROM usuarios WHERE usuario = '$usuario' AND senha = '$senha'";

        $query = $this->db->query($query);
        if (empty($query->result_array())) return false;
        else                               return true;
    }

}