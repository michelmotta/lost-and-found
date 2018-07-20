<header class="app-header"><a class="app-header__logo" href="index.html">App Server</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
            <ul class="dropdown-menu settings-menu dropdown-menu-right">
                <li>
                    <?= $this->Html->link(__('<i class="fa fa-user fa-lg"></i> Perfil') ,['controller' => 'Users', 'action' => 'view', $this->request->session()->read('Auth.User.id')], ['class' => 'dropdown-item', 'escape' => false]); ?>
                </li>
                <li>
                    <?= $this->Html->link(__('<i class="fa fa-sign-out fa-lg"></i> Sair') ,['controller' => 'Users', 'action' => 'logout'], ['class' => 'dropdown-item', 'escape' => false]) ?>
                </li>
            </ul>
        </li>
    </ul>
</header>