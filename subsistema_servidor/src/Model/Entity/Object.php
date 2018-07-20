<?php
namespace App\Model\Entity;

use Cake\ORM\Entity;

/**
 * Object Entity
 *
 * @property int $id
 * @property int $user_id
 * @property string $title
 * @property string $solved
 * @property string $type
 * @property \Cake\I18n\FrozenDate $date
 * @property string $image
 * @property string $description
 * @property \Cake\I18n\FrozenTime $created
 * @property \Cake\I18n\FrozenTime $modified
 *
 * @property \App\Model\Entity\User $user
 * @property \App\Model\Entity\Comment[] $comments
 */
class Object extends Entity
{

    /**
     * Fields that can be mass assigned using newEntity() or patchEntity().
     *
     * Note that when '*' is set to true, this allows all unspecified fields to
     * be mass assigned. For security purposes, it is advised to set '*' to false
     * (or remove it), and explicitly make individual fields accessible as needed.
     *
     * @var array
     */
    protected $_accessible = [
        'user_id' => true,
        'title' => true,
        'solved' => true,
        'type' => true,
        'date' => true,
        'image' => true,
        'description' => true,
        'created' => true,
        'modified' => true,
        'user' => true,
        'comments' => true
    ];
}
