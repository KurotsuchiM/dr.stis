<?php 
if(!defined('BASEPATH')) exit('No direct script access allowed');

/**
 * 
 */
class reservasi extends CI_Model
{
	
	function __construct()
	{
		parent::__construct();
		$this->load->database();
		$this->tabel='reservasi';
	}

	public function getList($reservasi_nim) {
		if(!$reservasi_nim) return;
		$this->db->where('reservasi_nim',$reservasi_nim);
		$query = $this->db->get('reservasi');
		return $query->result_array();
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
		$this->db->where('pengunjung_email',$pengunjung_email);
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