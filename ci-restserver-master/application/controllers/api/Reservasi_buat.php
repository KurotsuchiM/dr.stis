<?php 
if(!defined('BASEPATH')) exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

/**
 * 
 */
class Reservasi_buat extends REST_Controller
{
	protected $pengunjung_nim;
	function __construct()
	{
		parent::__construct();
		$this->load->model('reservasi');
		$this->load->model('pengunjung');

	}

	public function reservasi_get($reservasi_nim = '') {
		////print_r($reservasi_nim);
		$con = $reservasi_nim?array('pengunjung_nim' => $reservasi_nim):'';
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
		////print_r($reservasi_nim);

		$pengunjung = $this->reservasi_get($reservasi_nim);


		if($reservasi_nim){
			$con['returnType'] ='single';
			$con['conditions'] = array(
				'pengunjung_nim' => $reservasi_nim,
				'status_akun' =>1
			);
			$pemohon = $this->pengunjung->getRows($con);

			if($pemohon){

				$reservasi_tanggal = strip_tags($this->post('reservasi_tanggal'));
				$reservasi_jam = strip_tags($this->post('reservasi_jam'));
				$keluhan = strip_tags($this->post('keluhan'));
				//ketika kurang dari tanggal 15 maka yang peran itu dokter 1, sebaliknya

				$dokterJadwal = '199705042021011935';
				$reservasi = array(
					'reservasi_nim' => $reservasi_nim,
					'reservasi_dokter_nip' => $dokterJadwal,
					'reservasi_tanggal' => $reservasi_tanggal,
					'reservasi_jam' => $reservasi_jam,
					'keluhan' => $keluhan
				);
				////print_r($reservasi);
				if(!empty($reservasi_tanggal) && !empty($reservasi_jam) && !empty($keluhan)){
				$this->response([
					'data' => $pemohon['pengunjung_nama']
				], REST_Controller::HTTP_OK);

				$insert = $this->reservasi->insert($reservasi);
				////print_r($insert);
				if($insert) {
					$this->response([
						'pesan' =>'Data berhasil dikirm!',
						'data' => $insert
					], REST_Controller::HTTP_OK);

				}else{
					$this->response("Terdapat masalah, silakan ulangi proses!", REST_Controller::HTTP_BAD_REQUEST);
				}
			}else{
					$this->response("Data belum lengkap!",REST_Controller::HTTP_BAD_REQUEST);
				}
		}else{
			$this->response("Belum mulai apa-apa!",REST_Controller::HTTP_BAD_REQUEST);
		}

	}

	}
	}
 ?>