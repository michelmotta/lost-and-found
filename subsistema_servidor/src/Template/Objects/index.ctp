<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\Object[]|\Cake\Collection\CollectionInterface $objects
 */
?>
<div class="tile">
    <div class="tile-body">
        <div class="objects index large-9 medium-8 columns content">
            <h3><?= __('Objetos Cadastrados') ?></h3>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col"><?= $this->Paginator->sort('id', __('Código')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('title', __('Título')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('solved', __('Situação')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('type', __('Classificação')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('date', __('Data do Ocorrido')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('Criado') ?></th>
                        <th scope="col"><?= $this->Paginator->sort('Modificado') ?></th>
                        <th scope="col" class="actions"><?= __('Ações') ?></th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($objects as $object): ?>
                    <tr>
                        <td><?= $this->Number->format($object->id) ?></td>
                        <td><?= h($object->title) ?></td>
                        <td><?= h($object->solved) ?></td>
                        <td><?= h($object->type) ?></td>
                        <td><?= h($object->date) ?></td>
                        <td><?= h($object->created) ?></td>
                        <td><?= h($object->modified) ?></td>
                        <td class="actions">
                            <?= $this->Html->link(__('Ver'), ['action' => 'view', $object->id]) ?>
                            <?= $this->Html->link(__('Editar'), ['action' => 'edit', $object->id]) ?>
                            <?= $this->Form->postLink(__('Deletar'), ['action' => 'delete', $object->id], ['confirm' => __('Are you sure you want to delete # {0}?', $object->id)]) ?>
                        </td>
                    </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
            <div class="paginator">
                <ul class="pagination">
                    <?= $this->Paginator->first('<< ' . __('primeiro')) ?>
                    <?= $this->Paginator->prev('< ' . __('anterior')) ?>
                    <?= $this->Paginator->numbers() ?>
                    <?= $this->Paginator->next(__('próximo') . ' >') ?>
                    <?= $this->Paginator->last(__('último') . ' >>') ?>
                </ul>
                <p><?= $this->Paginator->counter(['format' => __('Página {{page}} de {{pages}}, mostrando {{current}} registro(s) de {{count}} total')]) ?></p>
            </div>
        </div>
    </div>
    </div>