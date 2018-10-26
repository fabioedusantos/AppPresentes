<?php
class PresentesModel extends CI_Model {
    public function get($id = null) { $query="SELECT * FROM presentes "; if(!empty($id)) $query.="WHERE id = $id "; $query.="ORDER BY dataCadastro DESC"; $query=$this->db->query($query); if(empty($query->result_array())) return null; return count($query->result_array())==1 && !empty($id)?$query->result_array()[0]:$query->result_array(); }
    public function insert($data) { return $this->db->insert('presentes', $data); }
    public function update($data, $id) { $this->db->where('id',$id); return $this->db->update('presentes',$data); }
}
