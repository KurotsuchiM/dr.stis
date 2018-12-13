<?php 
if(!defined('BASEPATH')) exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

/**
 * 
 */
class Reservasi extends REST_Controller
{
	protected $reservasi_nim;
	
	function __construct()
	{
		parent::__construct();
		date_default_timezone_set('Asia/Jakarta');
		$this->load->library('JWT/Auth');

		//Cek header apa terdapat authorization

		$token =$this->input->get_request_header('Kelompok4');

		//Jika tidak ada Autorization
		if(empty($token)) {
			$this->response(null, REST_Controller::HTTP_UNAUTHORIZED);
			die();
		}

		//jika ada tapi tidak valid
		try{
			$payload = Auth::checkToken($token);
		} catch(Exception $e){
			$this->response(null, REST_Controller::HTTP_UNAUTHORIZED);
			die();
		}

		//Simpan detail akun yang request, beupa reservasi_nim

		$this->reservasi_nim = $payload->data->reservasi_nim;
	}
	//kirim formulir
	public function index_post(){
		$this->load->model('Reservasi');
		$response['reservasi'] = $this->Reservasi->insertReservasi($this->post());
		if(!$response['reservasi']){
			$this->response($response,REST_Controller::HTTP_NOT_ACCEPTABLE);
			return;
		}
	}

	//halaman daftar reservasi pengunjung

	public function index_get($reservasi_nim=null){
		$this->load->model('Reservasi');

		if($reservasi_nim) {
			$daftar = $this->Reservasi->getList($reservasi_nim)->row_array();
			if(!$daftar) return;

			$pengunjung = $this->Reservasi->get_user_by_id($reservasi_nim);
			if($pengunjung) $daftar['pengunjung_nama'] = $pengunjung;


			$dokter = $this->Reservasi->get_dokter_by_id($reservasi_dokter_nip);
			if($dokter) $daftar['dokter_nama'] = $dokter;
			
		};
		$response['reservasi'] = $response;
		$this->response($response, REST_Controller::HTTP_OK);
		return;
	}
}
 ?>