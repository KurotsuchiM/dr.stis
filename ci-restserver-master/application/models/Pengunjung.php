<?php 
if(!defined('BASEPATH')) exit('No direct script access allowed');

/**
 * 
 */
class Pengunjung extends CI_Model
{
	
	function __construct()
	{
		parent::__construct();
		$this->load->database();
		$this->tabelPengunjung = 'pengunjung';
	}

	//Get Baris Pengunjung
	function getRows($params = array()) {
		$this->db->select('*');
		$this->db->from($this->tabelPengunjung);

		//fetch data by conditions
		if(array_key_exists("conditions", $params)) {
			foreach ($$params['conditions'] as $key => $value) {
				$this->db->where($key,$value);
			}
		}

		if(array_key_exists("pengujung_nim", $params)) {
			$this->db->where('pengujung_nim',$params['pengujung_nim']);
			$query = $this->db->get();
			$result = $query->row_array();
		}else{
			if(array_key_exists("start", $params)&&array_key_exists("limit", $params)) {
				$this->db->limit($params['limit'],$params['start']);
			}elseif (!array_key_exists("start",$params)&&array_key_exists("limit",$params)) {
				$this->db->limit($params['limit']);
			}

			if(array_key_exists("returnType", $params)&& $params['returnType']=='count') {
				$result = $this->db->count_all_results();
			}elseif(array_key_exists("returnType",$params)&& $params['returnType']=='single'){
				$query = $this->db->get();
				$result=($query->num_rows() > 0)?$query->row_array():false;
			}else{
				$query = $this->db->get();
				$result=($query->num_rows() > 0)?$query->result_array():false;
			}
		}
		return $result;
	}

	public function insert($data) {
		if(!array_key_exists("tanggal_buat", $data)) {
			$data['tanggal_buat'] = date("Y-m-d H:i:s");
		}
		if(!array_key_exists("modifikasi", $data)) {
			$data['modifikasi'] = date("Y-m-d H:i:s");
		}
		$insert = $this->db->insert($this->tabelPengunjung, $data);

		return $insert?$this->db->insert_id():false;
	}

	public function update($data,$pengujung_nim) {
		if(!array_key_exists('modifikasi', $data)){
			$data['modifikasi'] = date("Y-m-d H:i:s");
		}

		$update = $this->db->update($this->tabelPengunjung, $data, array('pengujung_nim' => $tabelPengunjung));

		return $update?true:false;
	}

	public function delete($pengujung_nim) {
		$delete = $this->db->delete('pengunjung', array('pengujung_nim' => $pengujung_nim));
		return $delete?true:false;
	}
}

 ?>