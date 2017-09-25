package nc.ui.ic.lhcalcday.self.view;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class InsterestCalListValueSetter implements IBillListPanelValueSetter {

	@Override
	public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas) {
		CircularlyAccessibleValueObject[] vos = new CircularlyAccessibleValueObject[allDatas.length];
		for (int i = 0; i < allDatas.length; i++) {
			vos[i] = ((CircularlyAccessibleValueObject) allDatas[i]);
		}
		//listPanel.setHeaderValueVO(vos);
		listPanel.setBodyValueVO(vos);
		BillModel headModel = listPanel.getHeadBillModel();
	
		
		if(headModel != null)
		{
			//处理参照信息
			if(listPanel.getBillListData().isMeataDataTemplate())
				headModel.loadLoadRelationItemValue();
			//处理装载公式
			headModel.execLoadFormula();
		}
	}

	@Override
	public void setHeaderRowData(BillListPanel listPanel, Object rowData,
			int row) {

		BillModel headModel = listPanel.getBillListData().getHeadBillModel();
		if (headModel != null) {
			headModel.setBodyRowVO((CircularlyAccessibleValueObject) rowData,
					row);
			// 处理参照信息
			if (listPanel.getBillListData().isMeataDataTemplate()) {
				BillItem[] items = headModel.getBodyItems();
				if (items != null && items.length > 0) {
					for (BillItem item : items)
						headModel.loadLoadRelationItemValue(row, item.getKey());
				}
			}
			// 处理装载公式
			headModel.execLoadFormulaByRow(row);
		}

	}

	@Override
	public void setBodyData(BillListPanel listPanel, Object selectedData) {
		// TODO Auto-generated method stub
        String str="";
        String str1="";
	}

}
