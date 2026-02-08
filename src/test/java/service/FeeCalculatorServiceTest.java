package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeeCalculatorServiceTest {
	
	private FeeCalculatorService feeCalculatorService;

	@BeforeEach
	void setUp() {
		feeCalculatorService = new FeeCalculatorServiceImpl();
	}

	@Test
	void testFeeForBalanceLessThanOrEqual100() {
		double fee1 = feeCalculatorService.calculateFee(0.0);
		assertEquals(20.0, fee1);
		double fee2 = feeCalculatorService.calculateFee(50.0);
		assertEquals(20.0, fee2);
		double fee3 = feeCalculatorService.calculateFee(100.0);
		assertEquals(20.0, fee3);
	}
	
	@Test
	void testFeeForBalanceGreaterThan100AndLessThanOrEqual500() {
		double fee1 = feeCalculatorService.calculateFee(100.1);
		assertEquals(15.0, fee1);
		double fee2 = feeCalculatorService.calculateFee(250.0);
		assertEquals(15.0, fee2);
		double fee3 = feeCalculatorService.calculateFee(500.0);
		assertEquals(15.0, fee3);
	}
	
	@Test
	void testFeeForBalanceGreaterThan500AndLessThanOrEqual1000() {
		double fee1 = feeCalculatorService.calculateFee(500.1);
		assertEquals(10.0, fee1);
		double fee2 = feeCalculatorService.calculateFee(750.0);
		assertEquals(10.0, fee2);
		double fee3 = feeCalculatorService.calculateFee(1000.0);
		assertEquals(10.0, fee3);
	}
	
	@Test
	void testFeeForBalanceGreaterThan1000AndLessThanOrEqual2000() {
		double fee1 = feeCalculatorService.calculateFee(1000.1);
		assertEquals(5.0, fee1);
		double fee2 = feeCalculatorService.calculateFee(1500.0);
		assertEquals(5.0, fee2);
		double fee3 = feeCalculatorService.calculateFee(2000.0);
		assertEquals(5.0, fee3);
	}
	
	@Test
	void testFeeForBalanceGreaterThan2000() {
		double fee1 = feeCalculatorService.calculateFee(2000.1);
		assertEquals(0.0, fee1);
		double fee2 = feeCalculatorService.calculateFee(5000.0);
		assertEquals(0.0, fee2);
	}
		
	@Test
	void testNegativeBalanceThrowsException() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, 
				() -> feeCalculatorService.calculateFee(-100.0)
				);
	    assertEquals("Balance cannot be negative", ex.getMessage());
	}
}
