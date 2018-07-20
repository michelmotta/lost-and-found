<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\Object $object
 */
use Cake\Routing\Router;
?>
<div class="tile">
    <div class="tile-body">
        <div class="objects view large-9 medium-8 columns content">
            <h3><?= h($object->title) ?></h3>
            <table class="table table-hover">
                <tr>
                    <th scope="row"><?= __('Título') ?></th>
                    <td><?= h($object->title) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Situação do Objeto') ?></th>
                    <td><?= h($object->solved) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Classificação') ?></th>
                    <td><?= h($object->type) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Id') ?></th>
                    <td><?= $this->Number->format($object->id) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Data do Ocorrido') ?></th>
                    <td><?= h($object->date) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Criado') ?></th>
                    <td><?= h($object->created) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Modificado') ?></th>
                    <td><?= h($object->modified) ?></td>
                </tr>
            </table>
            <div class="row">
                <div class="col-md-12">
                    <h4><?= __('Imagem') ?></h4> 
                    <center><img src="<?= Router::url('/', true) . "images_uploads/" . $object->image ?>" style="max-width: 200px;"/></center>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h4><?= __('Descrição do Ocorrido') ?></h4>
                    <?= $this->Text->autoParagraph(h($object->description)); ?>
                </div>
            </div>
            <div class="related">
                <h4><?= __('Comentários do Objeto') ?></h4>
                <?php if (!empty($object->comments)): ?>
                <table class="table table-hover table-sm">
                    <tr>
                        <th scope="col"><?= __('Código') ?></th>
                        <th scope="col"><?= __('Código do Objeto') ?></th>
                        <th scope="col"><?= __('Comentário') ?></th>
                        <th scope="col"><?= __('Criado') ?></th>
                        <th scope="col" class="actions"><?= __('Ações') ?></th>
                    </tr>
                    <?php foreach ($object->comments as $comments): ?>
                    <tr>
                        <td><?= h($comments->id) ?></td>
                        <td><?= h($comments->object_id) ?></td>
                        <td><?= h($comments->comment) ?></td>
                        <td><?= h($comments->created) ?></td>
                        <td class="actions">
                            <?= $this->Html->link(__('View'), ['controller' => 'Comments', 'action' => 'view', $comments->id]) ?>
                            <?= $this->Html->link(__('Edit'), ['controller' => 'Comments', 'action' => 'edit', $comments->id]) ?>
                            <?= $this->Form->postLink(__('Delete'), ['controller' => 'Comments', 'action' => 'delete', $comments->id], ['confirm' => __('Are you sure you want to delete # {0}?', $comments->id)]) ?>
                        </td>
                    </tr>
                    <?php endforeach; ?>
                </table>
                <?php endif; ?>
            </div>
        </div>
    </div>
</div>