<template>
  <div>
    <Card>
      <tables 
      ref="tables" 
      editable  
      search-place="top" 
      v-model="tableData" 
      :columns="columns">
      </tables>
    </Card>
  </div>
</template>

<script>
import Tables from '_c/tables'
import { getRepo } from '@/api/repo'
export default {
  name: 'repo',
  components: {
    Tables
  },
  data () {
    return {
      columns: [
        {title: 'Id', key: 'id', sortable: true},
        {title: 'Name', key: 'name', editable: false},
        {title: 'Create-Time', key: 'createdDate'},
        {title: 'Url', key: 'url', editable: false},
        {title: 'LocalPath', key: 'gitLocalPath', editable: false},
        {title: 'EepoSite', key: 'repoSiteId'}
        // {
        //   title: 'Handle',
        //   key: 'handle',
        //   options: ['delete'],
        //   button: [
        //     (h, params, vm) => {
        //       return h('Poptip', {
        //         props: {
        //           confirm: true,
        //           title: '你确定要删除吗?'
        //         },
        //         on: {
        //           'on-ok': () => {
        //             vm.$emit('on-delete', params)
        //             vm.$emit('input', params.tableData.filter((item, index) => index !== params.row.initRowIndex))
        //           }
        //         }
        //       }, [
        //         h('Button', '自定义删除')
        //       ])
        //     }
        //   ]
        // }
      ],
      tableData: []
    }
  },
  methods: {
    handleDelete (params) {
      console.log(params)
    },
    // exportExcel () {
    //   this.$refs.tables.exportCsv({
    //     filename: `table-${(new Date()).valueOf()}.csv`
    //   })
    // }
  },
  mounted () {
    getRepo().then(res => {
      console.log(res.data)
      this.tableData = res.data.data
    })
  }
}
</script>

<style>

</style>
