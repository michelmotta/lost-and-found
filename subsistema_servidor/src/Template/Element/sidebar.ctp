<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
	<li>
		<?= $this->Html->link(__('<i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Dashboard</span>'), ['controller' => 'Pages', 'action' => 'dashboard'], ['class' => 'app-menu__item', 'escape' => false]) ?>
	</li>
	<ul class="app-menu">
		<li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-archive"></i><span class="app-menu__label"><?= __('Objetos'); ?></span><i class="treeview-indicator fa fa-angle-right"></i></a>
			<ul class="treeview-menu">
				<li>
					<?= $this->Html->link(__('<i class="icon fa fa-th-list"></i> Gerenciar Objetos'), ['controller' => 'Objects', 'action' => 'index'], ['class' => 'treeview-item', 'escape' => false]) ?>
				</li>
				<li>
					<?= $this->Html->link(__('<i class="icon fa fa-plus"></i> Novo Objeto'), ['controller' => 'Objects', 'action' => 'add'], ['class' => 'treeview-item', 'escape' => false]) ?>
				</li>
			</ul>
		</li>
	</ul>
	<ul class="app-menu">
		<li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-comment"></i><span class="app-menu__label"><?= __('Comentários'); ?></span><i class="treeview-indicator fa fa-angle-right"></i></a>
			<ul class="treeview-menu">
				<li>
					<?= $this->Html->link(__('<i class="icon fa fa-th-list"></i> Gerenciar Comentários'), ['controller' => 'Comments', 'action' => 'index'], ['class' => 'treeview-item', 'escape' => false]) ?>
				</li>
				<li>
					<?= $this->Html->link(__('<i class="icon fa fa-plus"></i> Novo Comentários'), ['controller' => 'Comments', 'action' => 'add'], ['class' => 'treeview-item', 'escape' => false]) ?>
				</li>
			</ul>
		</li>
	</ul>
	<ul class="app-menu">
		<li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-user-circle-o"></i><span class="app-menu__label"><?= __('Usuários'); ?></span><i class="treeview-indicator fa fa-angle-right"></i></a>
			<ul class="treeview-menu">
				<li>
					<?= $this->Html->link(__('<i class="icon fa fa-th-list"></i> Gerenciar Usuários'), ['controller' => 'Users', 'action' => 'index'], ['class' => 'treeview-item', 'escape' => false]) ?>
				</li>
				<li>
					<?= $this->Html->link(__('<i class="icon fa fa-plus"></i> Novo Usuário'), ['controller' => 'Users', 'action' => 'add'], ['class' => 'treeview-item', 'escape' => false]) ?>
				</li>
			</ul>
		</li>
	</ul>
</aside>