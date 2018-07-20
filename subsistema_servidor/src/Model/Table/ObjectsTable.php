<?php
namespace App\Model\Table;

use Cake\ORM\Query;
use Cake\ORM\RulesChecker;
use Cake\ORM\Table;
use Cake\Validation\Validator;

/**
 * Objects Model
 *
 * @property \App\Model\Table\UsersTable|\Cake\ORM\Association\BelongsTo $Users
 * @property \App\Model\Table\CommentsTable|\Cake\ORM\Association\HasMany $Comments
 *
 * @method \App\Model\Entity\Object get($primaryKey, $options = [])
 * @method \App\Model\Entity\Object newEntity($data = null, array $options = [])
 * @method \App\Model\Entity\Object[] newEntities(array $data, array $options = [])
 * @method \App\Model\Entity\Object|bool save(\Cake\Datasource\EntityInterface $entity, $options = [])
 * @method \App\Model\Entity\Object patchEntity(\Cake\Datasource\EntityInterface $entity, array $data, array $options = [])
 * @method \App\Model\Entity\Object[] patchEntities($entities, array $data, array $options = [])
 * @method \App\Model\Entity\Object findOrCreate($search, callable $callback = null, $options = [])
 *
 * @mixin \Cake\ORM\Behavior\TimestampBehavior
 */
class ObjectsTable extends Table
{

    /**
     * Initialize method
     *
     * @param array $config The configuration for the Table.
     * @return void
     */
    public function initialize(array $config)
    {
        parent::initialize($config);

        $this->setTable('objects');
        $this->setDisplayField('title');
        $this->setPrimaryKey('id');

        $this->addBehavior('Timestamp');

        $this->belongsTo('Users', [
            'foreignKey' => 'user_id',
            'joinType' => 'INNER'
        ]);
        $this->hasMany('Comments', [
            'foreignKey' => 'object_id'
        ]);
    }

    /**
     * Default validation rules.
     *
     * @param \Cake\Validation\Validator $validator Validator instance.
     * @return \Cake\Validation\Validator
     */
    public function validationDefault(Validator $validator)
    {
        $validator
            ->integer('id')
            ->allowEmpty('id', 'create');

        $validator
            ->scalar('title')
            ->maxLength('title', 200)
            ->requirePresence('title', 'create')
            ->notEmpty('title');

        $validator
            ->scalar('solved')
            ->maxLength('solved', 150)
            ->requirePresence('solved', 'create')
            ->notEmpty('solved');

        $validator
            ->scalar('type')
            ->maxLength('type', 150)
            ->requirePresence('type', 'create')
            ->notEmpty('type');

        $validator
            ->date('date', ['dmy'])
            ->allowEmpty('date');

        $validator
            ->scalar('image')
            ->maxLength('image', 4294967295)
            ->allowEmpty('image');

        $validator
            ->scalar('description')
            ->allowEmpty('description');

        return $validator;
    }

    /**
     * Returns a rules checker object that will be used for validating
     * application integrity.
     *
     * @param \Cake\ORM\RulesChecker $rules The rules object to be modified.
     * @return \Cake\ORM\RulesChecker
     */
    public function buildRules(RulesChecker $rules)
    {
        $rules->add($rules->existsIn(['user_id'], 'Users'));

        return $rules;
    }
}
