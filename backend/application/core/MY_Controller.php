<?php

class MY_Controller extends CI_Controller {

    private $erro = "";
    public $valid = true;

    public function __construct() {
        parent::__construct();
    }

    public function put(){
        $this->defaultRequest("put");
    }

    public function post(){
        $this->defaultRequest("post");
    }

    public function get(){
        $this->defaultRequest("get");
    }

    public function delete(){
        $this->defaultRequest("delete");
    }

    private function defaultRequest($request) {
        $method = $this->getRequestType();

        if($method != $request) {
            $this->load->view('error_404');
        }

    }

    public function getRequestType() {
        return strtolower($_SERVER['REQUEST_METHOD']);
    }

    public function printWs($response) {
        echo json_encode(array(
            "connected" => true,
            "error" => !$this->valid ? $this->erro . ", RequestType: " . $this->getRequestType() : null,
            "response" => $this->valid ? $response : null
        ));
    }

    public function setError($erro){
        if(strlen($this->erro) > 0) $this->erro .= ", ";
        $this->erro .= $erro;
        $this->valid = false;
    }

    public function validateDate($date, $format = 'Y-m-d') {
        $d = DateTime::createFromFormat($format, $date);
        if($d && $d->format($format) == $date) {
            $date_now = new DateTime();

            if ($d > $date_now) {
                return true;
            }
        }
        return false;
    }

}