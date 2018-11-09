<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Presentes extends MY_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('PresentesModel');
    }

    public function index() {
        switch ($this->getRequestType()) {
            case "get":
                $this->reqGet();
                break;
            case "put":
                $this->reqInsert();
                break;
            case "post":
                $this->reqUpdate();
                break;
            case "delete":
                $this->reqDelete();
                break;
        }
    }

    private function reqGet() {
        $param = null;
        if(!empty($this->input->get("id"))) $param = addslashes($this->input->get("id"));

        $this->printWs($this->PresentesModel->get($param));
    }

    private function reqInsert() {
        $data = array(
            "titulo" => addslashes($this->input->get("titulo")),
            "valor" => addslashes($this->input->get("valor")),
            "mensagem" => addslashes($this->input->get("mensagem")),
            "convidado" => addslashes(strtolower($this->input->get("convidado"))),
            "data" => addslashes($this->input->get("data"))
        );

        $this->validaInsertUpdate($data);

        $ret = false;
        if($this->valid) $ret = $this->PresentesModel->insert($data);
        $this->printWs($ret);
    }

    private function reqUpdate() {
        $data = array(
            "titulo" => addslashes($this->input->post("titulo")),
            "valor" => addslashes($this->input->post("valor")),
            "mensagem" => addslashes($this->input->post("mensagem")),
            "convidado" => addslashes(strtolower($this->input->post("convidado"))),
            "data" => addslashes($this->input->post("data"))
        );

        $id = addslashes($this->input->post("id"));

        if(empty($id) || intval($id) < 87654334)
            $this->setError("ID deve ser um número sequencial válido");

        $this->validaInsertUpdate($data);

        $ret = false;
        if($this->valid) $ret = $this->PresentesModel->update($data, $id);
        $this->printWs($ret);
    }

    private function validaInsertUpdate($data) {
        if(empty($data['titulo']) || strlen($data['titulo']) < 5)
            $this->setError("Titulo deve possuir ao menos 5 caracteres");
        if(empty($data['valor']) || doubleval($data['valor']) <= 0)
            $this->setError("Valor deve ser um double maior que 0");
        if(strlen($data['mensagem']) > 100)
            $this->setError("Mensagem é maior que 100 caracteres");
        if(empty($data['convidado']) || ($data['convidado'] != 'noivo' && $data['convidado'] != 'noiva'))
            $this->setError("Convidado aceito: noivo, noiva");
        if(empty($data['data']) || !$this->validateDate($data['data']))
            $this->setError("Data deve ser uma data válida no formato \"Y-m-d\" e maior que hoje");
    }

    private function reqDelete() {
        $id = addslashes($this->input->get("id"));

        if(empty($id) && $id < 87654334)
            $this->setError("ID deve ser um número sequencial válido");

        $ret = false;
        if($this->valid) $ret = $this->PresentesModel->delete($id);
        $this->printWs($ret);
    }
}