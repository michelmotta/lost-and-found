<?php
namespace App\Controller;

use App\Controller\AppController;
use Cake\Event\Event;

/**
 * Comments Controller
 *
 * @property \App\Model\Table\CommentsTable $Comments
 *
 * @method \App\Model\Entity\Comment[]|\Cake\Datasource\ResultSetInterface paginate($object = null, array $settings = [])
 */
class CommentsController extends AppController
{

    public function beforeFilter(Event $event)
    {
        $this->Auth->allow([
            'comment',
            'listAllCommentsByObjectId',
            'addComment',
            'listAllCommentsByObjectUserId'
        ]);
    }

    /**
     * Index method
     *
     * @return \Cake\Http\Response|void
     */
    public function index()
    {
        $this->paginate = [
            'contain' => ['Objects', 'Users'],
            'conditions' => ['Objects.user_id' => $this->request->session()->read('Auth.User.id')],
        ];
        $comments = $this->paginate($this->Comments);

        $this->set(compact('comments'));
    }

    /**
     * View method
     *
     * @param string|null $id Comment id.
     * @return \Cake\Http\Response|void
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $comment = $this->Comments->get($id, [
            'contain' => ['Objects']
        ]);

        $this->set('comment', $comment);
    }

    /**
     * Add method
     *
     * @return \Cake\Http\Response|null Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $comment = $this->Comments->newEntity();
        if ($this->request->is('post')) {
            $comment = $this->Comments->patchEntity($comment, $this->request->getData());
            if ($this->Comments->save($comment)) {
                $this->Flash->success(__('The comment has been saved.'));

                return $this->redirect(['action' => 'index']);
            }
            $this->Flash->error(__('The comment could not be saved. Please, try again.'));
        }
        $objects = $this->Comments->Objects->find('list', ['limit' => 200]);
        $users = $this->Comments->Users->find('list', ['limit' => 200]);
        $this->set(compact('comment', 'objects', 'users'));
    }

    /**
     * Edit method
     *
     * @param string|null $id Comment id.
     * @return \Cake\Http\Response|null Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $comment = $this->Comments->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $comment = $this->Comments->patchEntity($comment, $this->request->getData());
            if ($this->Comments->save($comment)) {
                $this->Flash->success(__('The comment has been saved.'));

                return $this->redirect(['action' => 'index']);
            }
            $this->Flash->error(__('The comment could not be saved. Please, try again.'));
        }
        $objects = $this->Comments->Objects->find('list', ['limit' => 200]);
        $users = $this->Comments->Users->find('list', ['limit' => 200]);
        $this->set(compact('comment', 'objects', 'users'));
    }

    /**
     * Delete method
     *
     * @param string|null $id Comment id.
     * @return \Cake\Http\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $comment = $this->Comments->get($id);
        if ($this->Comments->delete($comment)) {
            $this->Flash->success(__('The comment has been deleted.'));
        } else {
            $this->Flash->error(__('The comment could not be deleted. Please, try again.'));
        }

        return $this->redirect(['action' => 'index']);
    }

    public function comment()
    {
        $comment = $this->Comments->newEntity();
        if ($this->request->is('post')) {
            $comment = $this->Comments->patchEntity($comment, $this->request->getData());
            if ($this->Comments->save($comment)) {

                $userTag = "user_" . $this->request->getData('object_user_id');
                $notification = $this->sendUserNotification($userTag);

                $this->Flash->success(__('The comment has been saved.'));

                return $this->redirect($this->referer());
            }
            $this->Flash->error(__('The comment could not be saved. Please, try again.'));
        }
        $objects = $this->Comments->Objects->find('list', ['limit' => 200]);
        $users = $this->Comments->Users->find('list', ['limit' => 200]);
        $this->set(compact('comment', 'objects', 'users'));
    }
    
    public function addComment()
    {
        $apiResponse = array();

        $comment = $this->Comments->newEntity();
        if ($this->request->is('post')) {
            $comment = $this->Comments->patchEntity($comment, $this->request->getData());
            if ($this->Comments->save($comment)) {
                $apiResponse['status'] = "success";
                $apiResponse['message'] = "Comentário salvo com sucesso";

                $userTag = "user_" . $this->request->getData('object_user_id');
                $notification = $this->sendUserNotification($userTag);

            }else{
                $apiResponse['status'] = "error";
                $apiResponse['message'] = "Erro ao salvar comentário";
            }
        }

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();

    }

    public function listAllCommentsByObjectId(){
        $apiResponse = array();
        
        $objectId = $this->request->getData('object_id');

        $comments = $this->Comments->find()
            ->contain(['Objects', 'Users'])
            ->where(['Comments.object_id' => $objectId])
            ->order(['Comments.created' => 'ASC']);

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Comentários do objeto " . $objectId;
        $apiResponse['data'] = $comments;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function listAllCommentsByObjectUserId(){
        $apiResponse = array();
        
        $objectUserId = $this->request->getData('object_user_id');

        $comments = $this->Comments->find()
            ->contain(['Objects', 'Users'])
            ->where(['Objects.user_id' => $objectUserId])
            ->order(['Comments.created' => 'ASC']);

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Comentários dos objetos do usuário" . $objectUserId;
        $apiResponse['data'] = $comments;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function sendUserNotification($userTag){

        $content = array("en" => 'Você recebeu um comentário');
    
        $fields = array(
            'app_id' => "f7f9cd81-9f8a-4cb7-b8d1-45c631db48c2",
            'big_picture' => "http://redesociais.com.br/notification/bigpicture_notification.png",
            'included_segments' => array('All'),
            'filters' => array(array("field" => "tag", "key" => "user_id", "relation" => "=", "value" => "$userTag")),
            'contents' => $content
        );
    
        $fields = json_encode($fields);

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, "https://onesignal.com/api/v1/notifications");
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json; charset=utf-8','Authorization: Basic YTRkOWM1ZTItZmUwMi00MTE1LWI2YzEtZDRiYTNmNjVmMzkx'));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
        curl_setopt($ch, CURLOPT_HEADER, FALSE);
        curl_setopt($ch, CURLOPT_POST, TRUE);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $fields);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);    
    
        $response = curl_exec($ch);
        curl_close($ch);
    
        return $response;
    }

}