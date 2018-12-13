<?php
defined('BASEPATH') OR exit('No direct script access allowed');

require_once APPPATH . '/libraries/JWT/JWT.php';

use Firebase\JWT\JWT;

class Auth {

    public static function checkToken($token) {
    	$CI =& get_instance();
    	return JWT::decode($token, 'dodolanodol', array('HS256'));
    }

    public static function generateToken($data) {

    	$CI =& get_instance();
    	return JWT::encode($data, 'dodolanodol');

    }

    public static function refreshToken() {

        return uniqid('', true);

    }
}