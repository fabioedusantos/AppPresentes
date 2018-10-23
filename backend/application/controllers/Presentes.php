<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class Presentes extends MY_Controller {
    public function __construct() { parent::__construct(); $this->load->model('PresentesModel'); }
    public function index() { if ($this->getRequestType() == "get") $this->reqGet(); }
    private function reqGet() {
        $param = null;
        if(!empty($this->input->get("id"))) $param = addslashes($this->input->get("id"));
        $this->printWs($this->PresentesModel->get($param));
    }
}
