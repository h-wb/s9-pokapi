const sidebars = {
    guide: [
        {
            title: 'Guide',
            collapsable: false,
            children: [
                '/guide/test'
            ]
        },
    ],

    api: [
        {
            title: 'Overview',
            collapsable: false,
            children: [
                '/api/overview'
            ]
        },
        {
            title: 'Définitions',
            collapsable: false,
            children: [
                '/api/definitions'
            ]
        },
        {
            title: 'Paths',
            collapsable: false,
            children: [
                '/api/paths'
            ]
        }
    ]
}

module.exports = {
    base: '/api-psw/',
    title: 'VuePress in Github Pages',
    description: 'Static website built with VuePress and deployed on Github Pages',

    themeConfig: {
        repo: 'h-wb/api-psw',
        docsDir: 'docs',
        sidebarDepth: 2,

        nav: [{
            text: 'Guide',
            link: '/guide/test'
        },
            {
                text: 'API Reference',
                link: '/api/overview'
            },
            {
                text: 'Release Notes',
                link: 'https://github.com/h-wb/api-psw/releases'
            }
        ],

        sidebar: {
            '/guide/': sidebars.guide,
            '/api/': sidebars.api,
            '/': sidebars.guide
        }
    }
};