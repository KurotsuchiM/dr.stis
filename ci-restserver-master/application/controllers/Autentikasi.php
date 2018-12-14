<?php 
if(!defined('BASEPATH')) exit('No direct script access allowed');
require (APPPATH . 'libraries/REST_Controller.php');

use Restserver\Libraries\REST_Controller;
/**
 * 
 */
class Autentikasi extends REST_Controller
{
	
	public function __construct()
	{
		parent::__construct();
		$this->load->model('pengunjung');
	}

	public function login_post() {
		//get data post
		$email = $this->post('email');
		$password = $this->post('password');

		//validasi data post
		if(!empty($email) && !empty($password)) {
			//cek jika ada pengunjung dengan credemtials
			$con['returnType'] = 'single';
			$con['conditions'] = array(
				'email' => $email,
				'password' =>$password,
				'status_akun' => 1
			);
			$pengunjung = $this->pengunjung->getRows($con);

			if($pengunjung){
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

	public function registration_post() {
		//get post data
		$pengunjung_nama = strip_tags($this->post('pengunjung_nama'));
		$pengujung_nim = strip_tags($this->post('pengujung_nim'));
		$status = strip_tags($this->post('status'));
		$password = $this->post('password');
		$konfirmasi_password = $this->post('konfirmasi_password');
		$email = strip_tags($this->post('email'));
		$nomor_telpon = strip_tags($this->post('nomor_telpon'));
		$jenis_kelamin = strip_tags($this->post('jenis_kelamin'));

		//validasi post data
		if(!empty($pengunjung_nama) && !empty($pengujung_nim) && !empty($password) && !empty($konfirmasi_password) && !empty($email)) {

			//cek jika email ada
			$con['returnType'] = 'count';
			$con['conditions'] = array(
				'email => $email',
			);
			$pengunjungCount = $this->user->getRows($con);

			if($pengunjungCount > 0) {
				$this->response("Email sudah pernah dipakai!", REST_Controller::HTTP_BAD_REQUEST);
			}else{
				//insert pengunjung 
				$pengunjungData = array(
					'pengunjung_nama' => $pengunjung_nama,
					'pengujung_nim' => $pengujung_nim,
					'status' => $status,
					'password' => $password,
					'konfirmasi_password' => $konfirmasi_password,
					'email' => $email,
					'nomor_telpon' => $nomor_telpon,
					'jenis_kelamin' => $jenis_kelamin
				);

				$insert = $this->pengunjung->insert($pengunjungData);

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
			$this->response("Regitrasi belum terisi semua!", REST_Controller::HTTP_BAD_REQUEST);
		}
	}

	/*
	* REQUEST_METHOD = 'GET'
	8http://localhost/ci-restserver-master/index.php/api/Autentikasi/pengunjung/16.9395
	*/
	
	public function pengunjung_get($pengujung_nim = 0) {
		//kembalikan semua data pengunjung jika tidak dispesifikasi
		//selain itu sesuai request
		$con = $pengujung_nim?array('pengujung_nim' => $pengujung_nim):'';
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

	public function pengunjung_put(){
		$pengujung_nim = $this->put('pengujung_nim');

		//Get post data
		$pengunjung_nama = strip_tags($this->post('pengunjung_nama'));
		$pengujung_nim = strip_tags($this->post('pengujung_nim'));
		$status = strip_tags($this->post('status'));
		$password = $this->post('password');
		$konfirmasi_password = $this->post('konfirmasi_password');
		$email = strip_tags($this->post('email'));
		$nomor_telpon = strip_tags($this->post('nomor_telpon'));
		$jenis_kelamin = strip_tags($this->post('jenis_kelamin'));

		//validasi 
		if(!empty($pengunjung_nama) && !empty($pengujung_nim) && !empty($password) && !empty($konfirmasi_password) && !empty($email)) {
			$pengunjungData = array();
			if(!empty($pengunjung_nama)) {
				$pengunjungData['pengunjung_nama'] = $pengunjung_nama;
			}
			if(!empty($pengujung_nim)) {
				$pengunjungData['pengujung_nim'] = $pengujung_nim;
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
			$update = $this->pengunjung->update($pengunjungData,$pengujung_nim);

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
		//print_r("expression");
	}
}
 ?>