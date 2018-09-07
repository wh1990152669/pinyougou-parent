//控制层
app.controller('typeTemplateController' ,function($scope,$controller   ,typeTemplateService ,brandService){
    //使用品牌服务方法实现查询，结果赋给变量
    $scope.brandList={data:[]};//品牌列表
    //读取品牌列表
    $scope.findBrandList=function(){
        brandService.selectOptionList().success(
            function(response){
                $scope.brandList={data:response};
            }
        );
    }
})