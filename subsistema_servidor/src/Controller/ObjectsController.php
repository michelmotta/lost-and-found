<?php
namespace App\Controller;

use App\Controller\AppController;
use Cake\Routing\Router;
use Cake\Event\Event;

/**
 * Objects Controller
 *
 * @property \App\Model\Table\ObjectsTable $Objects
 *
 * @method \App\Model\Entity\Object[]|\Cake\Datasource\ResultSetInterface paginate($object = null, array $settings = [])
 */
class ObjectsController extends AppController
{

    public function beforeFilter(Event $event)
    {
        $this->Auth->allow([
            'single',
            'addObject',
            'editObject',
            'listAllObjects',
            'listAllObjectsWithComments',
            'objectById',
            'objectByIdWithComments',
            'listAllMyObjects',
            'listAllMyObjectsWithComments',
            'myObjectById',
            'myObjectByIdWithComments'
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
            'contain' => ['Users'],
            'conditions' => ['Objects.user_id' => $this->request->session()->read('Auth.User.id')],
        ];
        $objects = $this->paginate($this->Objects);

        $this->set(compact('objects'));
    }

    /**
     * View method
     *
     * @param string|null $id Object id.
     * @return \Cake\Http\Response|void
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $object = $this->Objects->get($id, [
            'contain' => ['Users', 'Comments']
        ]);

        $this->set('object', $object);
    }

    /**
     * Add method
     *
     * @return \Cake\Http\Response|null Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $object = $this->Objects->newEntity();
        if ($this->request->is('post')) {
            
            $data = $this->request->getData();
            if(!empty($this->request->getData('image.name'))){

                $file = $this->request->data('image');
                $ext = substr(strtolower(strrchr($file['name'], '.')), 1);

                $file['name'] = md5(time().rand(1111, 999999)) . '.' . $ext;
                $filePath = WWW_ROOT . 'images_uploads' . DS . $file['name'];

                if(move_uploaded_file($file['tmp_name'], $filePath)){
                    $data['image'] = $file['name'];
                }else{
                    unset($data['image']);
                }
            }else{
                unset($data['image']);
            }

            $object = $this->Objects->patchEntity($object, $data);
            if ($this->Objects->save($object)) {
                $this->Flash->success(__('The object has been saved.'));

                return $this->redirect(['action' => 'index']);
            }
            $this->Flash->error(__('The object could not be saved. Please, try again.'));
            
        }
        $users = $this->Objects->Users->find('list', ['limit' => 200]);
        $this->set(compact('object', 'users'));
    }

    /**
     * Edit method
     *
     * @param string|null $id Object id.
     * @return \Cake\Http\Response|null Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $object = $this->Objects->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {

            $data = $this->request->getData();
            if(!empty($this->request->getData('image.name'))){

                $file = $this->request->data('image');
                $ext = substr(strtolower(strrchr($file['name'], '.')), 1);

                $file['name'] = md5(time().rand(1111, 999999)) . '.' . $ext;
                $filePath = WWW_ROOT . 'images_uploads' . DS . $file['name'];

                if(move_uploaded_file($file['tmp_name'], $filePath)){
                    $data['image'] = $file['name'];
                }else{
                    unset($data['image']);
                }
            }else{
                unset($data['image']);
            }

            $object = $this->Objects->patchEntity($object, $data);
            if ($this->Objects->save($object)) {
                $this->Flash->success(__('The object has been saved.'));

                return $this->redirect(['action' => 'index']);
            }
            $this->Flash->error(__('The object could not be saved. Please, try again.'));
        }
        $users = $this->Objects->Users->find('list', ['limit' => 200]);
        $this->set(compact('object', 'users'));
    }

    /**
     * Delete method
     *
     * @param string|null $id Object id.
     * @return \Cake\Http\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $object = $this->Objects->get($id);
        if ($this->Objects->delete($object)) {
            $this->Flash->success(__('The object has been deleted.'));
        } else {
            $this->Flash->error(__('The object could not be deleted. Please, try again.'));
        }

        return $this->redirect(['action' => 'index']);
    }

    public function single($id = null)
    {
        $this->viewBuilder()->setLayout('site');

        $object = $this->Objects->get($id, [
            'contain' => ['Users', 'Comments']
        ]);

        if($object->image){
            $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
        } 

        $this->set('object', $object);
    }

    public function addObject()
    {
        $apiResponse = array();

        $object = $this->Objects->newEntity();
        if ($this->request->is('post')) {
            
            $data = $this->request->getData();
            if(!empty($this->request->getData('image'))){

                $fileBase64 = $this->request->data('image');
                $fileName = uniqid() . '.png';
                $filePath = WWW_ROOT . 'images_uploads' . DS . $fileName;

                if(file_put_contents($filePath, base64_decode($fileBase64))){
                    $data['image'] = $fileName;
                }else{
                    unset($data['image']);
                }
            }else{
                unset($data['image']);
            }

            $object = $this->Objects->patchEntity($object, $data);
            if ($this->Objects->save($object)) {
                $apiResponse['status'] = "success";
                $apiResponse['message'] = "Objeto cadastrado com sucesso";
            }else{
                $apiResponse['status'] = "error";
                $apiResponse['message'] = "Erro ao cadastrar objeto";
            }

            $this->response->type('json');
            $this->response->body(json_encode($apiResponse));
            $this->response->send();
            $this->response->stop();
            
        }
    }

    public function editObject()
    {
        $apiResponse = array();
        $data = $this->request->getData();

        $object = $this->Objects->get($data['id'], [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {

            if(!empty($this->request->getData('image'))){

                $fileBase64 = $this->request->data('image');
                $fileName = uniqid() . '.png';
                $filePath = WWW_ROOT . 'images_uploads' . DS . $fileName;

                if(file_put_contents($filePath, base64_decode($fileBase64))){
                    $data['image'] = $fileName;
                }else{
                    unset($data['image']);
                }
            }else{
                unset($data['image']);
            }

            $object = $this->Objects->patchEntity($object, $data);
            if ($this->Objects->save($object)) {
                $apiResponse['status'] = "success";
                $apiResponse['message'] = "Objeto editado com sucesso";
            }else{
                $apiResponse['status'] = "error";
                $apiResponse['message'] = "Erro ao editar objeto";
            }

            $this->response->type('json');
            $this->response->body(json_encode($apiResponse));
            $this->response->send();
            $this->response->stop();
        }
    }

    public function listAllObjects(){

        $apiResponse = array();
        
        $objects = $this->Objects->find()
            ->where(['Objects.solved' => 'Não resolvido'])
            ->order(['Objects.id' => 'DESC']);

        foreach ($objects as $object) {
            if($object->image){
                $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
            } 
        }

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Lista de objetos cadastrados";
        $apiResponse['data'] = $objects;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function listAllObjectsWithComments()
    {

        $apiResponse = array();
        
        $objects = $this->Objects->find()
            ->contain(['Comments']);

        foreach ($objects as $object) {
            if($object->image){
                $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
            } 
        }

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Lista de objetos cadastrados";
        $apiResponse['data'] = $objects;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function objectById(){
        $apiResponse = array();
        
        $objectId = $this->request->getData('id');

        $objects = $this->Objects->find()
            ->where(['Objects.id' => $objectId]);

        foreach ($objects as $object) {
            if($object->image){
                $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
            } 
        }

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Meus Objetos";
        $apiResponse['data'] = $objects;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function objectByIdWithComments(){
        $apiResponse = array();
        
        $objectId = $this->request->getData('id');

        $objects = $this->Objects->find()
            ->contain(['Comments'])
            ->where(['Objects.id' => $objectId]);

        foreach ($objects as $object) {
            if($object->image){
                $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
            } 
        }

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Meus Objetos";
        $apiResponse['data'] = $objects;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function listAllMyObjects(){
        $apiResponse = array();
        
        $userId = $this->request->getData('user_id');

        $objects = $this->Objects->find()
            ->where(['Objects.user_id' => $userId]);

        foreach ($objects as $object) {
            if($object->image){
                $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
            } 
        }

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Meus Objetos";
        $apiResponse['data'] = $objects;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function listAllMyObjectsWithComments(){
        $apiResponse = array();
        
        $userId = $this->request->getData('user_id');

        $objects = $this->Objects->find()
            ->contain(['Comments'])
            ->where(['Objects.user_id' => $userId]);

        foreach ($objects as $object) {
            if($object->image){
                $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
            } 
        }

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Meus Objetos";
        $apiResponse['data'] = $objects;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function myObjectById(){
        $apiResponse = array();
        
        $objectId = $this->request->getData('object_id');
        $userId = $this->request->getData('user_id');

        $objects = $this->Objects->find()
            ->where(['Objects.id' => $objectId, 'Objects.user_id' => $userId]);

        foreach ($objects as $object) {
            if($object->image){
                $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
            } 
        }

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Meus Objetos";
        $apiResponse['data'] = $objects;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }

    public function myObjectByIdWithComments(){
        $apiResponse = array();
        
        $objectId = $this->request->getData('object_id');
        $userId = $this->request->getData('user_id');

        $objects = $this->Objects->find()
            ->contain(['Comments'])
            ->where(['Objects.id' => $objectId, 'Objects.user_id' => $userId]);

        foreach ($objects as $object) {
            if($object->image){
                $object->image = Router::url('/', true) . "images_uploads/" . $object->image;
            } 
        }

        $apiResponse['status'] = "success";
        $apiResponse['message'] = "Meus Objetos";
        $apiResponse['data'] = $objects;

        $this->response->type('json');
        $this->response->body(json_encode($apiResponse));
        $this->response->send();
        $this->response->stop();
    }
}
