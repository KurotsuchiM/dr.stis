<?php 

defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * 
 */
class modelApp extends CI_Model
{
	
	function __construct()
	{
		parent::__construct();

		date_default_timezone_set('Asia/Jakarta');
		$this->load->database();
	}

	function getAkunDetail($akun_id){
		return $this->db->get_where('pengunjung', 'akun_id'=$akun_id);
	}

	function registrasi(){
		$this->db->query("insert into('pengunjung_nama','pengujung_nim','status','password','konfirmasi_password','email','nomor_telpon','jenis_kelamin') VALUES ($pengunjung_nama,$pengunjung_nim,$status,$password,$konfirmasi_password,$email,$nomor_telepon,$jenis_kelamin);");
	}	

	function get_user_by_nim($nim){
		$que = $this->db->get_where('pengujung', 'pengujung_nim' = $nim);
		return $que->result_array();
		}
	}

	function login($email,$password) {
		//res akan terisi ketika email dan password ada
		$res = $this->db->get_where('pengunjung', $email = 'email');
		//coba nanti tulis jika ada email sama password disini
		return $res;

	}
}

 ?>