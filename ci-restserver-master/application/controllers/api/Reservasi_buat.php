<?php 
if(!defined('BASEPATH')) exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

/**
 * 
 */
class Reservasi_buat extends REST_Controller
{
	
	function __construct()
	{
		parent::__construct();
		$this->load->model('reservasi');
	}

	public function reservasi_get($reservasi_nim = '') {
		print_r($reservasi_nim);
		$con = $reservasi_nim?array('reservasi_nim' => $reservasi_nim):'';
		$reservasi = $this->reservasi->get_detail_reservasi($con);

		//cek jika pengunjung sudah ada
		if(!empty($reservasi)) {
			$this->response($reservasi, REST_Controller::HTTP_OK);
		}else{
			$this->response([
				'status' => FALSE,
				'message' => "Tidak ada reservasi!"],REST_Controller::HTTP_NOT_FOUND);
			}
		}

	public function daftar_post($reservasi_nim = '') {

		$this->load->model('pengunjung');
		if($reservasi_nim){
			$con['returnType'] ='single';
			$con['conditions'] = array(
				'pengunjung_nim' => $reservasi_nim
				'status_akun' =>1
			);
			$pemohon = $this->pengunjung->getRows($con);

			if($pemohon){

				$reservasi_tanggal = strip_tags($this->post('reservasi_tanggal'));
				$reservasi_jam = strip_tags($this->post('reservasi_jam'));
				$keluhan = strip_tags($this->post('keluhan'));
				//ketika kurang dari tanggal 15 maka yang peran itu dokter 1, sebaliknya
				$tanggal = strtotime($reservasi_tanggal);
				$res_tanggal = date('d', $tanggal);
				$batas = date('d','15');

				$dokterJadwal = ($res_tanggal<$batas)?'199705042021011935':'19980406202102356';
				$reservasi = array(
					'reservasi_nama' => $pemohon['pengunjung_nama',
					'reservasi_nim' => $reservasi_nim,
					'reservasi_dokter_nip' => $dokterJadwal,
					'reservasi_tanggal' => $reservasi_tanggal,
					'reservasi_jam' => $reservasi_jam,
					'keluhan' =. $keluhan
				);

				$this->response([
					'data' => $pemohon['pengunjung_nama']
				], REST_Controller::HTTP_OK);

				$insert = $this->reservasi->insert($reservasi);

				if($insert) {
					$this->response([
						'pesan' =>'Data berhasil dikirm!',
						'data' => $insert
					], REST_Controller::HTTP_OK);
				}else{
					$this->response("Terdapat masalah, silakan ulangi proses!", REST_Controller::HTTP_BAD_REQUEST);
				}
			}
		}
		//get post data
		$pengunjung_nama = strip_tags($this->post('pengunjung_nama'));
		//print_r($pengunjung_nama);

		$pengunjung_nim = strip_tags($this->post('pengunjung_nim'));
		//print_r($pengunjung_nim);
		
		$status = strip_tags($this->post('status'));
		//print_r($status);
			
		$password = $this->post('password');
		//print_r($password);
		
		$konfirmasi_password = $this->post('konfirmasi_password');
		//print_r($konfirmasi_password);
		
		$email = strip_tags($this->post('email'));
		//print_r($email);
		
		$nomor_telpon = strip_tags($this->post('nomor_telpon'));
		//print_r($nomor_telpon);
		
		$jenis_kelamin = strip_tags($this->post('jenis_kelamin'));
		//print_r($jenis_kelamin);
		

		//validasi post data
		if(!empty($pengunjung_nama) && !empty($pengunjung_nim) && !empty($password) && !empty($konfirmasi_password) && !empty($email)) {

			//cek jika email ada
			$con['returnType'] = 'count';
			$con['conditions'] = array(
				'email' => $email,
			);
		//	print_r("expression");
		//	print_r($con);
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
		//		print_r($pengunjungData);
				$insert = $this->pengunjung->insertPengunjung($pengunjungData);
		//		print_r($insert);
				//cek jika sudah dimasukkan
				if($insert) {
					$this->response([
						'status_akun' => TRUE,
						'pesan' => 'Pengunjung telah sukses terdaftar!',
						'data' => $insert
					], REST_Controller::HTTP_OK);
				}else{
					$this->response("Terdapat masalah, silakan ulangi proses!", REST_Controller::HTTP_BAD_REQUEST);
				}else{
					$this->response("Registrasi belum terisi semua!", REST_Controller::HTTP_BAD_REQUEST);
			}
		}
		}else{
			$this->response("Belum mulai apa-apa!",REST_Controller::HTTP_BAD_REQUEST);
		}

	}


	}
 ?>