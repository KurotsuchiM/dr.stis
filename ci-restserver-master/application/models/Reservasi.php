<?php 
if(!defined('BASEPATH')) exit('No direct script access allowed');

/**
 * 
 */
class Reservasi extends CI_Model
{
	
	function __construct()
	{
		parent::__construct();
		$this->load->database();
		$this->tabel='reservasi';
	}

	public function getList($params = array()) {
		
		$this->db->select('*');
		$this->db->from($this->tabel);

		//fetch data by conditions
		
		if(array_key_exists("conditions", $params)) {
			foreach ($params['conditions'] as $key => $value) {
				////print_r($params['conditions']);
				$this->db->where($key,$value);
			}
		}


		if(array_key_exists("reservasi_nim", $params)) {
			$this->db->where('reservasi_nim',$params['reservasi_nim']);
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

	//lihat apa bisa menspesifikasi dokter
	public function insert($data) {
		$insert = $this->db->insert($this->tabel,$data);
		if(empty($insert)) {
			return false;
		}else{
			return true;
		};
	}

	public function get_detail_reservasi($params= array()){

		$this->db->select('p.pengunjung_nama,
			p.pengunjung_nim,
			d.dokter_nip,
			d.dokter_nama,
			r.reservasi_tanggal,
			r.reservasi_jam,
			r.keluhan');
		$this->db->from('pengunjung p');
		$this->db->join('reservasi r','p.pengunjung_nim = r.reservasi_nim');
		$this->db->join('dokter d', 'd.dokter_nip = r.reservasi_dokter_nip');

		//fetch data by conditions
		
		if(array_key_exists("conditions", $params)) {
			foreach ($params['conditions'] as $key => $value) {
				////print_r($params['conditions']);
				$this->db->where($key,$value);
			}
		}
		////print_r($params);

		if(array_key_exists("reservasi_nim", $params)) {
			$this->db->where('p.reservasi_nim',$params['reservasi_nim']);
			$query = $this->db->get();
			////print_r($query);
			$result = $query->result_array();
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

	//dokter default tidak dapat mengubah identitas
	//dokter ditentukan oleh tanggal
	public function get_dokter($data) {
		if(array_key_exists('reservasi_dokter_nip', $data)) {
			$this->db->where('dokter_nip', $data['reservasi_dokter_nip']);
			$query = $this->db->get();
			$result = $query->row_array();
		}else{
			if(array_key_exists("start", $data)&&array_key_exists("limit", $data)) {
				$this->db->limit($data['limit'],$data['start']);
			}elseif (!array_key_exists("start", $data)&& !array_key_exists("limit", $data)) {
				$this->db->limit($data['limit']);
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



	public function getReservasi($reservasi_nim) {
		if(!$reservasi_nim) {
			return;
		}else{
			$query =$this->db->select('SELECT p.pengunjung_nama, r.reservasi_nim,r.reservasi_dokter_nip, d.dokter_nama, reservasi_tanggal, reservasi_jam, keluhan FROM reservasi r INNER JOIN pengunjung p ON r.reservasi_nim=p.pengunjung_nim INNER JOIN dokter d ON d.dokter_nip=r.reservasi_dokter_nip WHERE r.reservasi_nim=$reservasi_nim GROUP BY p.pengunjung_nim');
			return $query->result_array();
		}
	}

	public function delete($reservasi_nim) {
		$query = $this->db->query("DELETE FROM reservasi WHERE reservasi_nim=$reservasi_nim AND reservasi_tanggal=$reservasi_tanggal");
		return TRUE;
	}

	public function get_user_by_id($pengunjung_nim){
		$this->db->where('pengunjung_nim',$pengunjung_nim);
		$query = $this->db->get('pengunjung');
		return $query->result_array();
	}

	public function get_dokter_by_id($reservasi_dokter_nip,$reservasi_tanggal) {

		$this->db->where('dokter_nip',$reservasi_dokter_nip);
		$query = $this->db->get('dokter');
		return $query->result_array();
	}

	public function insertReservasi($content){
		$tanggal = date('d', $content['reservasi_tanggal']);
		if($tanggal < '15') {
			$reservasi_dokter_nip = '199705042021011935';
		}else{
			$reservasi_dokter_nip = '19980406202102356';
		}
		$content['reservasi_dokter_nip']=$reservasi_dokter_nip;
		
		if(!$content) return;
		$data = array(
			'pengunjung_nim' => $content['pengunjung_nim'],
			'reservasi_dokter_nip' => $content['reservasi_dokter_nip'],
			'reservasi_tanggal' => $content['reservasi_tanggal'],
			'reservasi_jam' =>$content['reservasi_jam'],
			'keluhan' => $content['keluhan']
		);

		$this->db->insert('reservasi', $data);

		return $content;
	}
}
 ?>