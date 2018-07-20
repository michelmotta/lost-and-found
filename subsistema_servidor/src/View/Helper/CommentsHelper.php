<?php
namespace App\View\Helper;

use Cake\View\Helper;
use Cake\ORM\TableRegistry;

class CommentsHelper extends Helper {

    public function userCommentName($userId)
    {
        if($userId){
          $this->Users = TableRegistry::get('Users');
          $user = $this->Users->get($userId);

          return $user->username;
        }

        return null; 
    }
}
