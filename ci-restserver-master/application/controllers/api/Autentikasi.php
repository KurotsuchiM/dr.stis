<?php 
if(!defined('BASEPATH')) exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;
/**
 * 
 */
class Autentikasi extends REST_Controller
{
	protected $user;
	
	public function __construct()
	{
		parent::__construct();
		$this->load->model('pengunjung');
		date_default_timezone_set('Asia/Jakarta');
        $this->load->database();
		$this->load->library('JWT/Auth');

	}

	/*
	* http://localhost/ci-restserver-master/index.php/api/Autentikasi/login/
	*/

	public function login_post() {
		//get data post
		$header = $this->input->get_request_header('X-API-KEY');

		if(empty($header)) {
			
			$this->response('Tidak berhak akses', REST_Controller::HTTP_UNAUTHORIZED);
			
			return;
		}

		$email = $this->post('email');
		$password = $this->post('password');

		//validasi data post
		if(!empty($email) && !empty($password)) {
			//cek jika ada pengunjung 
			$con['returnType'] = 'single';
			$con['conditions'] = array(
				'email' => $email,
				'password' =>$password,
				'status_akun' => 1
			);
			$pengunjung = $this->pengunjung->getRows($con);
			//////print_r($pengunjung);



			if($pengunjung){
				/*
		        * Catat waktu login
		        * Jika login pertama kali, lewat Android
		        */
				if ($pengunjung['loginPertama'] == '')
					$this->pengunjung->updateLoginPertama($pengunjung['pengunjung_nim']);
				else $this->pengunjung->updateLoginTerakhir($pengunjung['pengunjung_nim']);
				
				$this->response([
					'status_akun' => TRUE,
					'pesan' => 'Login Berhasil!',
					'data' =>$pengunjung
				], REST_Controller::HTTP_OK);
			}else{
				$this->response("Email atau password salah!", REST_Controller::HTTP_BAD_REQUEST);
			}
		}else{
			$this->response("Masukkan Email dan Password!", REST_Controller::HTTP_BAD_REQUEST);
		}

	}


	/*
	* http://localhost/ci-restserver-master/index.php/api/Autentikasi/registration/
	*/
	public function registration_post() {
		//////print_r("expression");
		//////print_r($this->user);
		//get post data
		$pengunjung_nama = strip_tags($this->post('pengunjung_nama'));
		//////print_r($pengunjung_nama);

		$pengunjung_nim = strip_tags($this->post('pengunjung_nim'));
		//////print_r($pengunjung_nim);
		
		$status = strip_tags($this->post('status'));
		//////print_r($status);
			
		$password = $this->post('password');
		//////print_r($password);
		
		$konfirmasi_password = $this->post('konfirmasi_password');
		//////print_r($konfirmasi_password);
		
		$email = strip_tags($this->post('email'));
		//////print_r($email);
		
		$nomor_telpon = strip_tags($this->post('nomor_telpon'));
		//////print_r($nomor_telpon);
		
		$jenis_kelamin = strip_tags($this->post('jenis_kelamin'));
		//////print_r($jenis_kelamin);
		

		//validasi post data
		if(!empty($pengunjung_nama) && !empty($pengunjung_nim) && !empty($password) && !empty($konfirmasi_password) && !empty($email)) {

			//cek jika email ada
			$con['returnType'] = 'count';
			$con['conditions'] = array(
				'email' => $email,
			);
		//	////print_r("expression");
		//	////print_r($con);
			$pengunjungCount = $this->pengunjung->getRows($con);

			if($pengunjungCount > 0) {
				$this->response("Email sudah pernah dipakai!", REST_Controller::HTTP_BAD_REQUEST);
			}else{
				//insert pengunjung 
				$pengunjungData = array(
					'pengunjung_nama' => $pengunjung_nama,
					'pengunjung_nim' => $pengunjung_nim,
					'status' => $status,
					'password' => $password,
					'konfirmasi_password' => $konfirmasi_password,
					'email' => $email,
					'nomor_telpon' => $nomor_telpon,
					'jenis_kelamin' => $jenis_kelamin
				);
		//		////print_r($pengunjungData);
				$insert = $this->pengunjung->insertPengunjung($pengunjungData);
		//		////print_r($insert);
				//cek jika sudah dimasukkan
				if($insert) {
					$this->response([
						'status_akun' => TRUE,
						'pesan' => 'Pengunjung telah sukses terdaftar!',
						'data' => $insert
					], REST_Controller::HTTP_OK);
				}else{
					$this->response("Terdapat masalah, silakan ulangi proses!", REST_Controller::HTTP_BAD_REQUEST);
				}
			}
		}else{
			$this->response("Registrasi belum terisi semua!", REST_Controller::HTTP_BAD_REQUEST);
		}
	}


	/*
	* REQUEST_METHOD = 'GET'
	8http://localhost/ci-restserver-master/index.php/api/Autentikasi/pengunjung/16.9395
	*/

	public function pengunjung_get($pengunjung_nim = '') {
		//kembalikan semua data pengunjung jika tidak dispesifikasi
		//selain itu sesuai request
		//////print_r($pengunjung_nim);
		$con = $pengunjung_nim?array('pengunjung_nim' => $pengunjung_nim):'';
		$pengunjung = $this->pengunjung->getRows($con);

		//cek jika pengunjung sudah ada
		if(!empty($pengunjung)) {
			$this->response($pengunjung, REST_Controller::HTTP_OK);
		}else{
			$this->response([
				'status' => FALSE,
				'message' => "TIdak ada pengunjung terpilih!"],REST_Controller::HTTP_NOT_FOUND);
		}
	}

	/*
	* REQUEST_METHOD = put
	* http://localhost/ci-restserver-master/index.php/api/Autentikasi/pengunjung/
	*/
	public function pengunjung_put(){
		//////print_r("expression");
		$pengunjung_nim = $this->put('pengunjung_nim');
		//////print_r($pengunjung_nim);
		
		////print_r("expression");

		//Get post data
		$pengunjung_nama = strip_tags($this->put('pengunjung_nama'));
		////print_r($pengunjung_nama);
		
		////print_r("expression");
		$pengunjung_nim = strip_tags($this->put('pengunjung_nim'));
		////print_r($pengunjung_nim);		

		$status = strip_tags($this->put('status'));
		////print_r($status);
		
		$password = $this->put('password');
		////print_r($password);
		
		$konfirmasi_password = $this->put('konfirmasi_password');
		////print_r($konfirmasi_password);
		
		$email = strip_tags($this->put('email'));
		////print_r($email);
		
		$nomor_telpon = strip_tags($this->put('nomor_telpon'));
		////print_r($nomor_telpon);
		
		$jenis_kelamin = strip_tags($this->put('jenis_kelamin'));
		////print_r($jenis_kelamin);
		
		//validasi 
		if(!empty($pengunjung_nama) && !empty($pengunjung_nim) && !empty(strip_tags($password)) && !empty(strip_tags($konfirmasi_password)) && !empty($email)) {
			////print_r("TEst");
		
			$pengunjungData = array();
			if(!empty($pengunjung_nama)) {
				$pengunjungData['pengunjung_nama'] = $pengunjung_nama;
			}
			if(!empty($pengunjung_nim)) {
				$pengunjungData['pengunjung_nim'] = $pengunjung_nim;
			}
			if(!empty($status)) {
				$pengunjungData['status'] = $status;
			}
			if(!empty($password)) {
				$pengunjungData['password'] = $password;
			}
			if(!empty($konfirmasi_password)) {
				$pengunjungData['konfirmasi_password'] = $konfirmasi_password;
			}
			if(!empty($email)) {
				$pengunjungData['email'] = $email;
			}
			if(!empty($nomor_telpon)) {
				$pengunjungData['nomor_telpon'] = $nomor_telpon;
			}
			if(!empty($jenis_kelamin)) {
				$pengunjungData['jenis_kelamin'] = $jenis_kelamin;
			}
			$update = $this->pengunjung->update($pengunjungData,$pengunjung_nim);

			if($update){
				$this->response([
					'status_akun' => TRUE,
					'pesan' => "Pengunjung info berhasil diperbarui!"], REST_Controller::HTTP_OK
				);
			}else{
				$this->response("Terjadi beberapa maslaah, coba proses lagi!", REST_Controller::HTTP_BAD_REQUEST);
			}
		}else{
			$this->response("Setidaknya da satu user diupdate!", REST_Controller::HTTP_BAD_REQUEST);
		}
	}
}
 ?>