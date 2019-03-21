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
import { getRepoSite } from '@/api/repo'
export default {
  name: 'repoSite',
  components: {
    Tables
  },
  data () {
    return {
      columns: [
        {title: 'Id', key: 'id', sortable: true},
        {title: 'Name', key: 'name', editable: false},
        {title: 'Create-Time', key: 'createdDate'},
        {title: 'Username', key: 'username', editable: false},
        {title: 'Password', key: 'password', editable: false},
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
    getRepoSite().then(res => {
      console.log(res.data)
      this.tableData = res.data.data
    })
  }
}
</script>

<style>

</style>
