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
    base: '/Pokapi/',
    title: 'Pokapi',
    description: 'API Pokemon créée dans le cadre du M2 GI',

    themeConfig: {
        repo: 'h-wb/Pokapi',
        docsDir: 'docs',
        sidebarDepth: 2,

        nav: [{
            text: 'Guide',
            link: '/guide/test'
        },
            {
                text: 'API',
                link: '/api/overview'
            },
            {
                text: 'Release Notes',
                link: 'https://github.com/h-wb/Pokapi/releases'
            }
        ],

        sidebar: {
            '/guide/': sidebars.guide,
            '/api/': sidebars.api,
            '/': sidebars.guide
        }
    }
};